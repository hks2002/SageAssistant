<template>
  <q-page>
    <WaitInputLottieVue v-if="!customerCode && isAuthorised('GESSIH')" />
    <ExceptionLottieVue :ErrorCode="403" v-if="!isAuthorised('GESSIH')" />

    <div class="row q-gutter-sm q-px-sm q-pt-sm" v-if="isAuthorised('GESSIH')">
      <QSelectAxios
        option-label="CustomerName"
        option-value="CustomerCode"
        data-url="/Data/CustomerHelper"
        :label="$t('Search Your Customer (Code or Name)[%% for all]')"
        input-style="font-weight:bolder;font-size:25px;text-transform:uppercase"
        popup-content-style="font-weight:bold;font-size:25px"
        popup-content-class="text-secondary"
        class="col-grow"
        @input-value="checkInputAll"
        @update:model-value="searchCustomer"
      />
      <q-toggle v-model="proSearch" label="Pro Search" class="col-1" />
      <q-input
        v-model="dateFrom"
        dense
        outlined
        debounce="1000"
        mask="date"
        type="date"
        :label="$t('From')"
        class="col-3"
      />
      <q-input
        v-model="dateTo"
        dense
        outlined
        debounce="1000"
        mask="date"
        type="date"
        :label="$t('To')"
        class="col-3"
      />
    </div>
    <q-list class="q-pa-sm" v-if="customerCode">
      <q-markup-table-invoice-pay-vue
        :customerCode="customerCode"
        :dateFrom="dateFrom"
        :dateTo="dateTo"
        :site="site"
        :proSearch="proSearch"
        :style="{ height: tableHeight + 'px' }"
      />
    </q-list>
  </q-page>
</template>

<script setup>
import { isAuthorised } from '@/assets/auth'
import { ebus } from '@/assets/ebus'
import { getCookies } from '@/assets/storage'
import QSelectAxios from '@/components/.controls/QSelectAxios.vue'
import QMarkupTableInvoicePayVue from '@/components/Financials/QMarkupTableInvoicePay.vue'
import ExceptionLottieVue from '@/components/lottie/ExceptionLottie.vue'
import WaitInputLottieVue from '@/components/lottie/WaitInputLottie.vue'
import { date } from 'quasar'
import { computed, onBeforeUnmount, ref } from 'vue'

/* eslint-disable */
const props = defineProps({
  pageHeight: { type: Number, default: 0 /* not passing  */ }
})

// common vars
const site = ref(getCookies('site'))

// page vars
const customerCode = ref(null)
const proSearch = ref(false)
const { formatDate, addToDate } = date
const nowTimeStamp = Date.now()
const fromTimeStamp = addToDate(nowTimeStamp, { years: -3 })
const dateFrom = ref(formatDate(fromTimeStamp, 'YYYY-MM-DD'))
const dateTo = ref(formatDate(nowTimeStamp, 'YYYY-MM-DD'))

// computed vars
const tableHeight = computed(() => {
  /** 56: QSelect height, 16: padding height */
  return props.pageHeight - 56 - 8
})

// check if searchAll
const checkInputAll = (inputText) => {
  if (inputText === '%%') {
    customerCode.value = '%%'
  }
}

const searchCustomer = (Code) => {
  customerCode.value = Code
}

// events
ebus.on('changeSite', () => {
  site.value = getCookies('site')
})
onBeforeUnmount(() => {
  ebus.off('changeSite')
})
</script>
