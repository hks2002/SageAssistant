<!--
 * @Author         : Robert Huang<56649783@qq.com>
 * @Date           : 2022-05-29 19:44:59
 * @LastEditors    : Robert Huang<56649783@qq.com>
 * @LastEditTime   : 2022-05-29 19:45:01
 * @FilePath       : \web2\src\components\lottie\ExceptionPage.vue
 * @CopyRight      : Dedienne Aerospace China ZhuHai
-->
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
/* eslint-disable */
const props = defineProps(['ErrorCode'])
</script>
