<template>
  <div
    class="flex justify-center items-center"
    style="height: 100vh"
  >
    <q-card
      style="width: 60vw; min-width: 300px"
      :flat="!isLgXs"
    >
      <q-card-section :horizontal="isLgXs">
        <!-- horizontal=true make col inactive -->
        <q-card-section class="col-8">
          <!-- horizontal true make col doesn't works -->
          <lottie-player
            src="/json/working-on-laptop-in-office.json"
            background="transparent"
            autoplay
            loop
          />
        </q-card-section>
        <q-separator
          v-if="isLgXs"
          vertical
          inset
        />
        <q-card-section
          align="center"
          class="col-4"
        >
          <!-- horizontal=true make col inactive -->
          <div class="text-h3 text-primary">
            {{ $t("Sage Assistant") }}
          </div>
          <q-banner
            dense
            class="bg-white text-red text-subtitle1"
            style="min-height:100px"
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
              {{ $t("Login") }}
            </q-btn>
          </q-form>
        </q-card-section>
      </q-card-section>
    </q-card>
  </div>
</template>

<script>
import { defineComponent, ref, onMounted } from 'vue'
import { useQuasar } from 'quasar'
import { useRouter } from 'vue-router'
import { ebus } from 'boot/ebus'
import { axios } from 'boot/axios'
import '@lottiefiles/lottie-player'
import { setToken, removeToken, setLoginData } from 'assets/storage'
import { setAuthority } from 'assets/auth'

export default defineComponent({
  name: 'Login',
  setup(props, ctx) {
    const $q = useQuasar()
    const $router = useRouter()
    const isLgXs = $q.screen.gt.xs
    const isPwd = ref(true)
    const username = ref('')
    const password = ref('')
    const loading = ref(false)
    const loginMessage = ref('')

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
      const auth = authToken('basic', username.value, password.value)

      await axios.post(
        '/auth/login/submit',
        { username: username.value },
        { headers: { authorization: auth } }
      ).then(
        (response) => {
          setToken(auth)
          fetchLoginData()
          fetchAuthorityData()
          $router.push('/')
        },
        (error) => {
          removeToken()
          loginMessage.value = error.data.$diagnoses[0].$message
        }
      ).finally(() => {
        loading.value = false
        $q.loadingBar.stop()
      })
    }

    const fetchLoginData = async () => {
      // post must have {}, if data is empty, otherwise forbidden
      await axios.post(
        '/api1/syracuse/collaboration/syracuse/userProfiles/$template/$workingCopies',
        {}
      ).then(
        (response) => {
          const loginData = {}
          loginData.userInfo = response.data.user.firstName + ' ' + response.data.user.lastName + '(' + response.data.user.email + ')'
          loginData.sageInfo = response.data.productName + ' ' + response.data.selectedEndpoint.description
          loginData.userId = response.data.user.$uuid
          loginData.roleId = response.data.selectedRole.$uuid
          loginData.locale = response.data.selectedLocale.code
          loginData.localeDesc = response.data.selectedLocale.description
          setLoginData(loginData)
          ebus.emit('updateLoginData')
        },
        (error) => {
          console.debug(error)
        }
      )
    }

    const fetchAuthorityData = async () => {
      await axios.post(
        '/api1/syracuse/collaboration/syracuse/pages(\'x3.erp.EXPLOIT.home.$navigation\')',
        {}
      ).then(
        (response) => {
          setAuthority(response.data)
        },
        (error) => {
          console.debug(error)
        }
      )
    }

    return {
      isLgXs,
      isPwd,
      username,
      password,
      loginMessage,
      loading,
      doLogin,
      checkEnterKey
    }
  }
})
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
