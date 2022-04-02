<template>
  <q-page class="flex flex-center">
    <Vue3Lottie animationLink="/json/working.json" background="transparent" />
    <div class="text-h1 text-primary">
      {{ $t('Welcome to SageAssistant') }}
    </div>
  </q-page>
</template>

<script>
import { defineComponent } from 'vue'
import { ebus } from 'boot/ebus'
import { useI18n } from 'vue-i18n'
import { useQuasar } from 'quasar'
import { Vue3Lottie } from 'vue3-lottie'

export default defineComponent({
  name: 'PageIndex',
  components: { Vue3Lottie },
  setup() {
    const $q = useQuasar()
    // const { t } = useI18n({ useScope: 'global' })
    ebus.emit('closeLeftDrawer')
    ebus.emit('activePage', 'Analysis')

    $q.loadingBar.stop()
    // return { t }
  }
})
</script>
