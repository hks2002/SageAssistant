<template>
  <Vue3Lottie
    :animationLink="'/json/' + ErrorCode + '.json'"
    :height="400"
    :width="400"
    background="transparent"
    class="flex fixed-center"
  />
</template>

<script setup>
import { Vue3Lottie } from 'vue3-lottie'

const props = defineProps(['ErrorCode'])
</script>
