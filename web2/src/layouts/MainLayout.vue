<!--
 * @Author         : Robert Huang<56649783@qq.com>
 * @Date           : 2022-03-25 11:01:23
 * @LastEditors    : Robert Huang<56649783@qq.com>
 * @LastEditTime   : 2022-05-29 21:53:29
 * @FilePath       : \web2\src\layouts\MainLayout.vue
 * @CopyRight      : Dedienne Aerospace China ZhuHai
-->
<template>
  <q-layout view="hHh Lpr lFf">
    <PageHeader :style="{ height: pageHeaderHeight + 'px' }" />

    <!-- mini-to-overlay let the main body size frezon-->
    <q-drawer
      v-model="leftDrawerOpen"
      show-if-above
      :mini="miniState"
      :mini-width="drawerMiniWidth"
      :width="150"
      mini-to-overlay
      bordered
      @mouseover="miniState = false"
      @mouseout="miniState = true"
    >
      <MenuItems />
    </q-drawer>

    <q-page-container :style="{ height: pageContainerHeight + 'px' }">
      <MainTabsVue :tabHeight="pageContainerHeight" />
      <!-- if don't want to use multi tabs, use router-view instead of it  -->
      <!-- <router-view /> -->
    </q-page-container>

    <PageFooter :style="{ height: pageFooterHeight + 'px' }" />
  </q-layout>
</template>

<script setup>
import { ebus } from '@/assets/ebus'
import MainTabsVue from '@/layouts/MainTabs.vue'
import MenuItems from '@/layouts/MenuItems.vue'
import PageFooter from '@/layouts/PageFooter.vue'
import PageHeader from '@/layouts/PageHeader.vue'
import { onBeforeMount, onBeforeUnmount, ref } from 'vue'

/** why it always be true when mounted */
const leftDrawerOpen = ref(true)
const pageHeaderHeight = 40
const pageFooterHeight = 18
const pageContainerHeight = ref(600)
const pageContainerWidth = ref(800)
const drawerMiniWidth = 40
const miniState = ref(true)

const toggleLeftDrawer = () => {
  leftDrawerOpen.value = !leftDrawerOpen.value
  updatePageWidth()
}

const closeLeftDrawer = () => {
  leftDrawerOpen.value = false
  updatePageWidth()
}

const updatePageHeight = () => {
  // save to global
  pageContainerHeight.value =
    window.innerHeight - pageHeaderHeight - pageFooterHeight
}

const updatePageWidth = () => {
  // save to global
  // console.debug('leftDrawerOpen', leftDrawerOpen.value)
  if (leftDrawerOpen.value) {
    pageContainerWidth.value = window.innerWidth - drawerMiniWidth
  } else {
    pageContainerWidth.value = window.innerWidth
  }
}

window.onresize = () => {
  updatePageHeight()
  updatePageWidth()
}

onBeforeMount(() => {
  // sub components mounted before layout, need set global pageBodyHeight for sub components
  updatePageHeight()
  updatePageWidth()
})

// event handing
ebus.on('toggleLeftDrawer', toggleLeftDrawer)
ebus.on('closeLeftDrawer', closeLeftDrawer)

onBeforeUnmount(() => {
  ebus.off('toggleLeftDrawer', toggleLeftDrawer)
  ebus.off('closeLeftDrawer', closeLeftDrawer)
})
</script>
