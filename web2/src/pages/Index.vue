<template>
  <q-page class="flex flex-center">
    <lottie-player
      src="/json/working.json"
      background="transparent"
      autoplay
      loop
      style="height: 400px"
    />
    <div class="text-h1 text-primary">
      {{ $t("Welcome to SageAssistant") }}
    </div>
  </q-page>
</template>

<script>
import { defineComponent } from 'vue'
import { useI18n } from 'vue-i18n'
import { useQuasar } from 'quasar'
import '@lottiefiles/lottie-player'

export default defineComponent({
  name: 'PageIndex',
  setup() {
    const $q = useQuasar()
    // const { t } = useI18n({ useScope: 'global' })

    $q.loadingBar.stop()
    // return { t }
  }
})
</script>
