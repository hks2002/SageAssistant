<template>
  <q-tabs
    v-model="tab"
    dense
    align="left"
    class="text-grey"
    active-color="primary"
    indicator-color="primary"
    narrow-indicator
  >
    <q-tab name="Fapiao" label="Fapiao" />
    <q-tab name="InvoicePay" label="Invoice Pay" />
    <q-tab name="Balance" label="Balance" />
  </q-tabs>

  <q-separator />

  <q-tab-panels v-model="tab" animated keep-alive>
    <q-tab-panel name="Fapiao" style="padding: 0px">
      <fapiao-vue />
    </q-tab-panel>
    <q-tab-panel name="InvoicePay" style="padding: 0px">
      <invoice-pay-main-vue />
    </q-tab-panel>
    <q-tab-panel name="Balance" style="padding: 0px">
      <balance-vue />
    </q-tab-panel>
  </q-tab-panels>
</template>

<script setup>
import { ref } from 'vue'
import FapiaoVue from 'components/Financials/Fapiao.vue'
import InvoicePayMainVue from 'src/components/Financials/InvoicePayMain.vue'
import BalanceVue from 'src/components/Financials/Balance.vue'

const tab = ref('Fapiao')
</script>
<style lang="scss" scoped></style>
