<template>
  <q-page :key="timer">
    <financials-main-vue />
  </q-page>
</template>

<script>
import { defineComponent } from 'vue'
import { ebus } from 'boot/ebus'
import FinancialsMainVue from 'src/components/Financials/FinancialsMain.vue'

export default defineComponent({
  name: 'Stock',

  components: {
    FinancialsMainVue
  },

  setup(props, ctx) {
    ebus.emit('closeLeftDrawer')
    ebus.emit('activePage', 'Financials')

    return {
      timer: new Date().getTime()
    }
  }
})
</script>
<style lang='scss' scoped>
</style>
