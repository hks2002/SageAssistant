<template>
  <lottie-player
    src="/json/404.json"
    background="transparent"
    style="width: 400px"
    class="fixed-center"
    autoplay
    loop
  />
</template>

<script>
import { defineComponent } from 'vue'
import { useQuasar } from 'quasar'
import '@lottiefiles/lottie-player'

export default defineComponent({
  name: 'Error404',
  setup() {
    const $q = useQuasar()
    $q.loadingBar.stop()
  }
})
</script>
