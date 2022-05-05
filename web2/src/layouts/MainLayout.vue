<template>
  <q-layout view="hHh Lpr lFf">
    <PageHeader :height="pageHeaderHeight" />

    <!-- mini-to-overlay let the main body size frezon-->
    <q-drawer
      v-model="leftDrawerClose"
      show-if-above
      :mini="miniState"
      :mini-width="drawerMiniWidth"
      :width="150"
      mini-to-overlay
      bordered
      @mouseover="miniState = false"
      @mouseout="miniState = true"
    >
      <Menus />
    </q-drawer>

    <q-page-container expand>
      <q-scroll-area :style="'height:' + pageBodyHeight + 'px'">
        <router-view />
      </q-scroll-area>
    </q-page-container>

    <PageFooter :height="pageFooterHeight" />
  </q-layout>
</template>

<script setup>
import { ref, onBeforeUnmount, onMounted, onBeforeMount } from 'vue'
import { useQuasar } from 'quasar'
import { ebus } from 'boot/ebus'
import Menus from './Menus.vue'
import PageFooter from './PageFooter.vue'
import PageHeader from './PageHeader.vue'

const $q = useQuasar()
const leftDrawerClose = ref(false)
const pageHeaderHeight = ref(40)
const pageFooterHeight = ref(18)
const pageBodyHeight = ref(800)
const drawerMiniWidth = ref(40)
const pageBodyWidth = ref(600)
const miniState = ref(true)

const toggleLeftDrawer = () => {
  console.debug('[evt] toggleLeftDrawer')
  leftDrawerClose.value = !leftDrawerClose.value
}
const closeLeftDrawer = () => {
  console.debug('[evt] closeLeftDrawer')
  leftDrawerClose.value = true
}

const updatePageHeight = () => {
  pageBodyHeight.value =
    window.innerHeight - pageHeaderHeight.value - pageFooterHeight.value
  // save to global
  $q.pageBodyHeight = pageBodyHeight.value
  console.debug('pageHeight:' + pageBodyHeight.value)
}

const updatePageWidth = () => {
  if (leftDrawerClose.value) {
    pageBodyWidth.value = window.innerWidth
  } else {
    pageBodyWidth.value = window.innerWidth - drawerMiniWidth.value
  }
  // save to global
  $q.pageBodyWidth = pageBodyWidth.value
  console.debug('pageWidth:' + pageBodyWidth.value)
}

window.onresize = () => {
  updatePageHeight()
  updatePageWidth()
}

onBeforeMount(() => {
  console.debug('onBeforeMount MainLayout')
  // sub components mounted before layout, need set global pageBodyHeight for sub components
  updatePageHeight()
  updatePageWidth()
})
onMounted(() => {
  console.debug('onMounted MainLayout')
})

// event handing
ebus.on('toggleLeftDrawer', toggleLeftDrawer)
onBeforeUnmount(() => {
  ebus.off('toggleLeftDrawer')
})
ebus.on('closeLeftDrawer', closeLeftDrawer)
onBeforeUnmount(() => {
  ebus.off('closeLeftDrawer')
})
</script>
