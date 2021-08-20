<template>
  <q-layout view="hHh Lpr lFf">
    <PageHeader />

    <!-- mini-to-overlay let the main body size frezon-->
    <q-drawer
      v-model="leftDrawerOpen"
      show-if-above
      :mini="miniState"
      :mini-width="40"
      :width="150"
      mini-to-overlay
      bordered
      @mouseover="miniState = false"
      @mouseout="miniState = true"
    >
      <Menus />
    </q-drawer>

    <q-page-container>
      <q-scroll-area :style="pageHeightStyle">
        <router-view />
      </q-scroll-area>
    </q-page-container>

    <PageFooter />
  </q-layout>
</template>

<script>
import { defineComponent, ref, onBeforeUnmount, onMounted } from 'vue'
import { ebus } from 'src/boot/ebus'
import Menus from 'src/components/Menus.vue'
import PageFooter from 'src/components/PageFooter.vue'
import PageHeader from 'src/components/PageHeader.vue'

export default defineComponent({
  name: 'MainLayout',

  components: {
    Menus,
    PageFooter,
    PageHeader
  },

  setup() {
    const leftDrawerOpen = ref(false)
    const pageHeightStyle = ref('')

    const toggleLeftDrawer = () => {
      console.debug('[evt] toggleLeftDrawer')
      leftDrawerOpen.value = !leftDrawerOpen.value
    }
    const closeLeftDrawer = () => {
      console.debug('[evt] closeLeftDrawer')
      leftDrawerOpen.value = false
    }

    const updatePageHeight = () => {
      pageHeightStyle.value = 'height:' + (document.body.clientHeight - 74) + 'px'
    }

    window.onresize = () => {
      updatePageHeight()
    }

    onMounted(() => {
      updatePageHeight()
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

    // return them to vue template
    return {
      leftDrawerOpen,
      pageHeightStyle,
      miniState: ref(true)
    }
  }
})
</script>
