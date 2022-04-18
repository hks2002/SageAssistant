<!--  -->
<template>
  <q-header elevated :style="'height:' + height + 'px'">
    <q-toolbar>
      <q-btn
        flat
        dense
        round
        icon="fas fa-outdent"
        aria-label="Menu"
        @click="toggleLeftDrawer"
      />
      <q-btn
        v-show="$q.screen.gt.xs"
        dense
        flat
        round
        size="sm"
        class="q-mr-xs"
      >
        <q-avatar dense>
          <img src="/imgs/logo.svg" style="background-color: white" />
        </q-avatar>
      </q-btn>
      <q-toolbar-title>Sage Assistant{{ $route.path }}</q-toolbar-title>
      <div class="q-gutter-xs q-ml-sm row items-center no-wrap">
        <q-select
          dense
          v-model="site"
          @update:model-value="setCookieSite"
          :options="siteList"
        >
        </q-select>
        <q-btn
          type="a"
          target="_blank"
          size="sm"
          dense
          round
          href="https://srvsyr01"
        >
          <q-avatar size="sm">
            <img src="https://srvsyr01/favicon.ico" />
            <q-tooltip v-if="$q.screen.gt.sm">
              {{ sageInfo }}
            </q-tooltip>
          </q-avatar>
        </q-btn>
        <q-btn
          round
          dense
          size="sm"
          icon="fas fa-question-circle"
          @click="showHelp"
        >
          <q-tooltip>{{ $t('Help') }}</q-tooltip>
        </q-btn>
        <q-btn dense round size="sm" icon="fas fa-bell">
          <q-badge
            v-if="totalInformCount > 0"
            color="negative"
            style="padding: 2px 4px"
            title-color="white"
            floating
          >
            {{ totalInformCount }}
          </q-badge>
        </q-btn>
        <span>{{ userInfo }}</span>
        <q-btn
          dense
          flat
          size="sm"
          icon="fas fa-sign-out-alt"
          @click="doLogout"
        >
          <q-tooltip>{{ $t('Exit') }}</q-tooltip>
        </q-btn>
      </div>
    </q-toolbar>
  </q-header>
</template>

<script>
import { defineComponent, ref, onBeforeUnmount, onMounted } from 'vue'
import { useQuasar } from 'quasar'
import { useRouter } from 'vue-router'
import { infoDialog, notifyError } from 'assets/common'
import { ebus } from 'boot/ebus'
import {
  getToken,
  removeToken,
  getLoginData,
  getCookies,
  setCookies
} from 'assets/storage'
import { axios } from 'boot/axios'

export default defineComponent({
  name: 'PageHeader',
  props: {
    height: {
      type: Number,
      required: false,
      default: 40
    }
  },
  components: {},
  setup() {
    const $router = useRouter()

    const site = ref(null)
    const siteList = ref(null)
    const userInfo = ref('Your name')
    const sageInfo = ref('Sage')
    const totalInformCount = ref(0)

    const toggleLeftDrawer = () => {
      ebus.emit('toggleLeftDrawer')
    }

    const setCookieSite = () => {
      setCookies('site', site.value, 3600 * 24 * 7)
      ebus.emit('changeSite', site.value)
    }

    const showHelp = () => {
      infoDialog('In Developping')
    }

    const doLogout = () => {
      removeToken()
      $router.push('/Login')
    }

    const updateLoginData = () => {
      const loginData = getLoginData()
      if (loginData) {
        userInfo.value = loginData.userInfo
        sageInfo.value = loginData.sageInfo
      }
    }

    updateLoginData()

    if (getCookies('site')) {
      site.value = getCookies('site')
    } else {
      site.value = 'ZHU'
      setCookieSite()
    }

    onMounted(() => {
      console.debug('onMounted PageHeader')

      axios
        .get('/Data/Sites')
        .then((response) => {
          siteList.value = response.data
          setCookies('siteList', siteList.value, 3600 * 24 * 7)
        })
        .catch((e) => {
          notifyError('Loading Sites Failed!')
        })
    })

    // event handing
    ebus.on('updateLoginData', () => {
      updateLoginData()
    })
    onBeforeUnmount(() => {
      ebus.off('updateLoginData')
    })

    return {
      site,
      siteList,
      setCookieSite,
      userInfo,
      sageInfo,
      totalInformCount,
      toggleLeftDrawer,
      showHelp,
      doLogout
    }
  }
})
</script>
<style lang="scss" scoped>
.q-field__native > span {
  color: white;
}
.q-field__append {
  color: white;
}
</style>
