<template>
  <q-page class="flex flex-center" style="height: 100%">
    <q-card style="width: 60vw; min-width: 300px" :flat="!isLgXs">
      <q-card-section :horizontal="isLgXs">
        <!-- horizontal=true make col inactive -->
        <q-card-section class="col-8">
          <!-- horizontal true make col doesn't works -->
          <Vue3Lottie
            animationLink="/json/working-on-laptop-in-office.json"
            background="transparent"
          />
        </q-card-section>
        <q-separator v-if="isLgXs" vertical inset />
        <q-card-section align="center" class="col-4">
          <!-- horizontal=true make col inactive -->
          <div class="text-h3 text-primary">
            {{ $t('Sage Assistant') }}
          </div>
          <q-banner
            dense
            class="bg-white text-red text-subtitle1"
            style="min-height: 100px"
          >
            {{ $t(loginMessage) }}
          </q-banner>
          <q-form>
            <!--  -->
            <q-input
              v-model="username"
              standout="bg-teal text-white"
              bottom-slots
              :label="$t('Your PC Account')"
              autocomplete="username"
              @keydown="checkEnterKey($event)"
            >
              <template v-slot:prepend>
                <q-icon name="fas fa-user" />
              </template>
            </q-input>
            <!--  -->
            <q-input
              v-model="password"
              clearable
              class="login-input"
              standout="bg-teal text-white"
              bottom-slots
              :label="$t('Password')"
              :type="isPwd ? 'password' : 'text'"
              autocomplete="current-password"
              @keydown="checkEnterKey($event)"
            >
              <template v-slot:prepend>
                <q-icon name="fas fa-key" />
              </template>
              <template v-slot:append>
                <q-icon
                  :name="isPwd ? 'fas fa-eye-slash' : 'fas fa-eye'"
                  class="cursor-pointer"
                  v-on:click="isPwd = !isPwd"
                />
              </template>
            </q-input>

            <!--  -->
            <q-btn
              :loading="loading"
              class="login-btn bg-login-card-input"
              text-color="white"
              unelevated
              label
              style="font-size: large"
              @click="doLogin"
            >
              {{ $t('Login') }}
            </q-btn>
          </q-form>
        </q-card-section>
      </q-card-section>
    </q-card>
  </q-page>
</template>

<script setup>
import { setAuthority } from '@/assets/auth'
import { axios } from '@/assets/axios'
import { axiosGet, axiosPost } from '@/assets/axiosActions'
import { ebus } from '@/assets/ebus'
import { removeToken, setLoginData, setToken } from '@/assets/storage'
import { useQuasar } from 'quasar'
import { onMounted, ref } from 'vue'
import { useRouter } from 'vue-router'
import { Vue3Lottie } from 'vue3-lottie'

const $q = useQuasar()
const $router = useRouter()
const isLgXs = $q.screen.gt.xs
const isPwd = ref(true)
const username = ref('')
const password = ref('')
const loading = ref(false)
const loginMessage = ref('')

onMounted(() => {
  removeToken()
})

// This function is from Sage login page, login.js
const authToken = (type, login, password) => {
  // convert to UTF8 (only works for characters with code points up to 65535)
  let s = login + ':' + password
  let i = s.length
  while (--i >= 0) {
    const c = s.charCodeAt(i)
    if (c > 127) {
      if (c < 2047) {
        s =
          s.substr(0, i) +
          String.fromCharCode(192 | (c >> 6), 128 | (c & 0x3f)) +
          s.substr(i + 1)
      } else {
        s =
          s.substr(0, i) +
          String.fromCharCode(
            224 | (c >> 12),
            128 | ((c >> 6) & 0x3f),
            128 | (c & 0x3f)
          ) +
          s.substr(i + 1)
      }
    }
  }
  // !!! btoa not supported on IE??
  switch (type) {
    case 'basic':
      return 'Basic ' + window.btoa(s)
    case 'sageerpx3':
      return 'sageerpx3 ' + window.btoa(s)
    default:
      return null
  }
}

const checkEnterKey = (event) => {
  if (event.keyCode === 13) {
    doLogin()
  }
}

const doLogin = async () => {
  loading.value = true
  const token = authToken('basic', username.value, password.value)

  await axiosPost(
    '/auth/login/submit',
    { username: username.value },
    { headers: { authorization: token } }
  )
    .then(
      (data) => {
        if (data === '') {
          /* empty means succuss */
          setToken(token)
          fetchLoginData()
          fetchAuthorityData()
          $router.push({ name: 'Home' })
        } else {
          removeToken()
          const reg = /(?<=login_onload\().*(?=\))/

          const txt = reg.exec(data)[0]
          loginMessage.value = JSON.parse(txt).errorMessage
        }
      },
      () => {
        removeToken()
        loginMessage.value = t('Wrong happans!')
      }
    )
    .finally(() => {
      loading.value = false
      $q.loadingBar.stop()
    })
}

const fetchLoginData = async () => {
  // post must have {}, if data is empty, otherwise forbidden
  // sage return 201 status, don't use axiosPost
  await axios
    .post(
      '/api1/syracuse/collaboration/syracuse/userProfiles/$template/$workingCopies',
      {}
    )
    .then((response) => {
      const data = response.data
      const loginData = {}
      loginData.userInfo = `${data.user.firstName} ${data.user.lastName}(${data.user.email})`
      loginData.sageInfo = `${data.productName} ${data.selectedEndpoint.description}`
      loginData.userId = data.user.$uuid
      loginData.roleId = data.selectedRole.$uuid
      loginData.locale = data.selectedLocale.code
      loginData.localeDesc = data.selectedLocale.description
      setLoginData(loginData)
      ebus.emit('updateLoginData')
    })
}

const fetchAuthorityData = async () => {
  await axiosGet(
    "/api1/syracuse/collaboration/syracuse/pages('x3.erp.EXPLOIT.home.$navigation')"
  ).then((response) => {
    setAuthority(response)
  })
}
</script>

<style lang="scss" scoped>
.login-btn {
  font-size: large;
  margin-top: 0px;
  margin-bottom: 20px;
  width: 100%;
}

.bg-login-card-input {
  background: linear-gradient(to right, $secondary, $primary);
  transition: all 0.3s ease-in-out;
  background-size: 200% auto;
}

.bg-login-card-input:hover {
  background-position: right center;
  box-shadow: 0 12px 20px -11px $primary-h;
}
</style>
