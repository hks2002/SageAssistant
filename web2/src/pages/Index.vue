<template>
  <q-page class="flex flex-center">
    <Vue3Lottie animationLink="/json/working.json" background="transparent" />
    <div class="text-h1 text-primary">
      {{ $t('Welcome to SageAssistant') }}
    </div>
  </q-page>
</template>

<script setup>
import { ebus } from 'boot/ebus'
import { useI18n } from 'vue-i18n'
import { useQuasar } from 'quasar'
import { Vue3Lottie } from 'vue3-lottie'

const $q = useQuasar()
// const { t } = useI18n({ useScope: 'global' })
ebus.emit('closeLeftDrawer')
ebus.emit('activePage', 'Analysis')

$q.loadingBar.stop()
// return { t }
</script>
