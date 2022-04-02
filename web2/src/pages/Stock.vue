<template>
  <q-page :key="timer">
    <stock-main-vue />
  </q-page>
</template>

<script>
import { defineComponent } from 'vue'
import { ebus } from 'boot/ebus'
import StockMainVue from 'src/components/stock/StockMain.vue'

export default defineComponent({
  name: 'Stock',

  components: {
    StockMainVue
  },

  setup(props, ctx) {
    ebus.emit('closeLeftDrawer')
    ebus.emit('activePage', 'Stock')

    return {
      timer: new Date().getTime()
    }
  }
})
</script>
<style lang='scss' scoped>
</style>
