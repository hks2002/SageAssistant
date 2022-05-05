<template>
  <q-page :key="timer">
    <financials-main-vue />
  </q-page>
</template>

<script setup>
import { ebus } from 'boot/ebus'
import FinancialsMainVue from 'src/components/Financials/FinancialsMain.vue'

ebus.emit('closeLeftDrawer')
ebus.emit('activePage', 'Financials')
const timer = new Date().getTime()
</script>
<style lang="scss" scoped></style>
