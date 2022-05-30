<!--
 * @Author         : Robert Huang<56649783@qq.com>
 * @Date           : 2022-03-25 11:01:23
 * @LastEditors    : Robert Huang<56649783@qq.com>
 * @LastEditTime   : 2022-05-28 22:58:05
 * @FilePath       : \web2\src\layouts\MenuItems.vue
 * @CopyRight      : Dedienne Aerospace China ZhuHai
-->
<template>
  <q-scroll-area class="fit">
    <q-list>
      <MenuLink v-for="link in Links" :key="link.title" v-bind="link" />
    </q-list>
  </q-scroll-area>
</template>

<script setup>
import { ebus } from '@/assets/ebus'
import MenuLink from '@/layouts/MenuLink'
import { onBeforeUnmount, onMounted, ref } from 'vue'

const Links = ref([])

// events
onMounted(() => {
  Links.value = require('@/components/menuItem/menuItems').default
})

ebus.on('changeLanguage', async () => {
  delete require.cache[require.resolve('@/components/menuItem/menuItems')]
  Links.value = require('@/components/menuItem/menuItems').default
})

onBeforeUnmount(() => {
  ebus.off('changeLanguage')
})
</script>
