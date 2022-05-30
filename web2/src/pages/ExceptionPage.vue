<template>
  <page>
    <ExceptionLottieVue :ErrorCode="ErrorCode" />
  </page>
</template>

<script setup>
import ExceptionLottieVue from '@/components/lottie/ExceptionLottie.vue'

/* eslint-disable */
const props = defineProps(['ErrorCode'])
</script>
