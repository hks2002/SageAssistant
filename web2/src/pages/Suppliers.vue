<!--  -->
<template>
  <q-page :key="timer">
    <suppliers-main-vue />
  </q-page>
</template>

<script>
import { defineComponent } from 'vue'

import { ebus } from 'boot/ebus'
import SuppliersMainVue from 'src/components/suppliers/SuppliersMain.vue'

export default defineComponent({
  name: 'Suppliers',
  components: {
    SuppliersMainVue
  },

  setup() {
    ebus.emit('closeLeftDrawer')
    ebus.emit('activePage', 'Suppliers')

    return {
      timer: new Date().getTime()
    }
  }
})
</script>
<style lang='scss' scoped>
</style>
