<!--  -->
<template>
  <q-page :key="timer">
    <customers-main-vue />
  </q-page>
</template>

<script>
import { defineComponent } from 'vue'

import { ebus } from 'boot/ebus'
import CustomersMainVue from 'src/components/customers/CustomersMain.vue'

export default defineComponent({
  name: 'Customers',
  components: {
    CustomersMainVue
  },

  setup() {
    ebus.emit('closeLeftDrawer')
    ebus.emit('activePage', 'Customers')

    return {
      timer: new Date().getTime()
    }
  }
})
</script>
<style lang='scss' scoped>
</style>
