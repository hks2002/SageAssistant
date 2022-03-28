<template>
  <lottie-player
    :src="'/json/' + ErrorCode +'.json'"
    background="transparent"
    style="width: 400px"
    class="fixed-center"
    autoplay
    loop
  />
</template>

<script>
import { defineComponent } from 'vue'
import '@lottiefiles/lottie-player'

export default defineComponent({
  name: 'Exception',
  props: ['ErrorCode'],
  setup(props) {}
})
</script>
