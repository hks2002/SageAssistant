<!--  -->
<template>
  <q-page :key="timer">
    <products-main-vue />
  </q-page>
</template>

<script>
import { defineComponent } from 'vue'
import { ebus } from 'boot/ebus'
import ProductsMainVue from 'src/components/products/ProductsMain.vue'

export default defineComponent({
  name: 'Products',

  components: {
    ProductsMainVue
  },

  setup() {
    ebus.emit('closeLeftDrawer')
    ebus.emit('activePage', 'Products')

    return { timer: new Date().getTime() }
  }
})
</script>
<style lang="scss" scoped></style>
