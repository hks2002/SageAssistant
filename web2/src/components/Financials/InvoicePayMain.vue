<template>
  <q-card>
    <!-- <Vue3Lottie
      animationLink="/json/waiting-input.json"
      :height="600"
      :width="600"
      class="fixed-center"
      v-if="!customerCode && isAuthorised('GESITM')"
      style="z-index: -1"
    /> -->
    <vue3-lottie
      animationLink="/json/403.json"
      :height="600"
      :width="600"
      class="fixed-center"
      v-if="!isAuthorised('GESSIH')"
    />
    <div class="row q-gutter-sm q-px-sm q-pt-sm">
      <q-select-input
        option-label="CustomerName"
        option-value="CustomerCode"
        data-url="/Data/CustomerHelper?CustomerCodeOrName="
        emit-to="searchCustomer"
        :label="$t('Search Your Customer (Code or Name)[%% for all]')"
        popup-content-class="text-secondary"
        class="col-grow"
        :disable="!isAuthorised('GESSIH')"
        @input-value="checkInputAll"
      />
      <q-toggle v-model="proSearch" label="Pro Search" class="col-1" />
      <q-input
        v-model="dateFrom"
        outlined
        debounce="1000"
        mask="date"
        type="date"
        :label="$t('From')"
        :disable="!isAuthorised('GESSIH')"
        class="col-3"
      />
      <q-input
        v-model="dateTo"
        outlined
        debounce="1000"
        mask="date"
        type="date"
        :label="$t('To')"
        :disable="!isAuthorised('GESSIH')"
        class="col-3"
      />
    </div>
    <q-list class="q-pa-sm">
      <q-markup-table-invoice-pay-vue
        :customerCode="customerCode"
        :dateFrom="dateFrom"
        :dateTo="dateTo"
        :proSearch="proSearch"
        :style="{ height: tableHeight + 'px', width: tableWidth + 'px' }"
      />
    </q-list>
  </q-card>
</template>

<script>
import { defineComponent, ref, onBeforeMount, onBeforeUnmount } from 'vue'
import { useQuasar, date } from 'quasar'
import { ebus } from 'boot/ebus'
import { isAuthorised } from 'assets/auth'
import { Vue3Lottie } from 'vue3-lottie'
import QSelectInput from 'components/.controls/QSelectInput.vue'
import QMarkupTableInvoicePayVue from './QMarkupTableInvoicePay.vue'

export default defineComponent({
  name: 'InvoicePayMain',

  components: {
    QSelectInput,
    Vue3Lottie,
    QMarkupTableInvoicePayVue
  },

  setup(props, ctx) {
    const $q = useQuasar()
    const customerCode = ref(null)
    const { formatDate, addToDate } = date

    const nowTimeStamp = Date.now()
    const fromTimeStamp = addToDate(nowTimeStamp, { years: -3 })
    const dateFrom = ref(formatDate(fromTimeStamp, 'YYYY-MM-DD'))
    const dateTo = ref(formatDate(nowTimeStamp, 'YYYY-MM-DD'))
    const proSearch = ref(false)

    const tableHeight = ref(250)
    const tableWidth = ref(600)

    // check if searchAll
    const checkInputAll = (inputText) => {
      if (inputText === '%%') {
        customerCode.value = '%%'
      }
    }

    const update = (Code) => {
      customerCode.value = Code
    }

    onBeforeMount(() => {
      console.debug('onBeforeMount QCardInvoicePay')
      // should consider element margin/padding value
      tableWidth.value = $q.pageBodyWidth - 8 * 2
      tableHeight.value = $q.pageBodyHeight - 36 - 72 - 8
    })

    // event handing
    ebus.on('searchCustomer', (Code) => {
      update(Code)
    })
    onBeforeUnmount(() => {
      ebus.off('searchCustomer')
    })

    return {
      customerCode,
      dateFrom,
      dateTo,
      isAuthorised,
      checkInputAll,
      proSearch,
      tableHeight,
      tableWidth
    }
  }
})
</script>
