<template>
  <lottie-player
    src="/json/waiting-input.json"
    background="transparent"
    autoplay
    loop
    style="width: 600px; height: 600px"
    class="fixed-center"
  />
</template>

<script>
import { defineComponent } from 'vue'
import { useQuasar } from 'quasar'
import '@lottiefiles/lottie-player'

export default defineComponent({
  name: 'Waiting',
  setup() {
    const $q = useQuasar()
    $q.loadingBar.stop()
  }
})
</script>
