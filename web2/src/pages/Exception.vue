<template>
  <Vue3Lottie
    :animationLink="'/json/' + ErrorCode +'.json'"
    :height="200"
    :width="200"
    background="transparent"
    style="width: 400px"
    class="fixed-center"
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
