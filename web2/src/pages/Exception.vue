<template>
  <Vue3Lottie
    :animationLink="'/json/' + ErrorCode +'.json'"
    :height="400"
    :width="400"
    background="transparent"
    class="flex fixed-center"
  />
</template>

<script>
import { defineComponent } from 'vue'
import { Vue3Lottie } from 'vue3-lottie'

export default defineComponent({
  name: 'Exception',
  props: ['ErrorCode'],
  components: {
    Vue3Lottie
  },
  setup(props) {}
})
</script>
