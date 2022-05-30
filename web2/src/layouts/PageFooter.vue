<!--
 * @Author         : Robert Huang<56649783@qq.com>
 * @Date           : 2022-03-25 11:01:23
 * @LastEditors    : Robert Huang<56649783@qq.com>
 * @LastEditTime   : 2022-05-28 22:58:34
 * @FilePath       : \web2\src\layouts\PageFooter.vue
 * @CopyRight      : Dedienne Aerospace China ZhuHai
-->
<template>
  <q-footer class="footer" :style="'height:' + height + 'px'">
    <q-bar class="fit flex-center footer">
      <div>
        <span class="q-pa-ms">{{ $t('Developed by Dedienne China') }} </span>
      </div>
    </q-bar>
  </q-footer>
</template>

<script setup>
/* eslint-disable */
const props = defineProps({
  height: {
    type: Number,
    required: false,
    default: 18
  }
})
</script>
<style lang="scss" scoped>
.footer {
  background-color: grey;
}
.footer div {
  color: white;
  font-size: 10px;
}
</style>
