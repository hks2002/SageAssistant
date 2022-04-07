<template>
  <vue3-lottie
    animationLink="/json/403.json"
    v-if="!isAuthorised('GESSIH')"
    class="fixed-center"
  />
  <q-card v-else>
    <div class="row q-gutter-sm q-px-sm q-pt-sm">
      <q-select-input
        option-label="CustomerName"
        option-value="CustomerCode"
        data-url="/Data/CustomerHelper?CustomerCodeOrName="
        emit-to="searchCustomer"
        :label="$t('Search Your Customer (Code or Name)[%% for all]')"
        popup-content-class="text-secondary"
        class="col-grow"
        @input-value="checkInputAll"
      />
      <q-toggle
        v-model="proSearch"
        label="Pro Search"
        class="col-1"
      />
      <q-input
        v-model="dateFrom"
        outlined
        debounce="1000"
        mask="date"
        type="date"
        :label="$t('From')"
        class="col-3"
      />
      <q-input
        v-model="dateTo"
        outlined
        debounce="1000"
        mask="date"
        type="date"
        :label="$t('To')"
        class="col-3"
      />
    </div>
    <Vue3Lottie
      animationLink="/json/waiting-input.json"
      :style="{ height: tableHeight + 'px', width: tableWidth + 'px' }"
      v-if="!customerCode"
    />
    <q-list class="q-pa-sm" v-else>
      <q-markup-table-invoice-pay-vue
        :customerCode="customerCode"
        :dateFrom="dateFrom"
        :dateTo="dateTo"
        :site="site"
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
import { getCookies } from 'src/assets/storage'
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

    const site = ref(getCookies('site'))

    const tableHeight = ref(250)
    const tableWidth = ref(600)

    // check if searchAll
    const checkInputAll = (inputText) => {
      if (inputText === '%%') {
        customerCode.value = '%%'
      }
    }

    onBeforeMount(() => {
      console.debug('onBeforeMount QCardInvoicePay')
      // should consider element margin/padding value
      tableWidth.value = $q.pageBodyWidth - 8 * 2
      tableHeight.value = $q.pageBodyHeight - 36 - 72 - 8
    })

    // event handing
    ebus.on('searchCustomer', (Code) => {
      customerCode.value = Code
    })
    onBeforeUnmount(() => {
      ebus.off('searchCustomer')
    })
    ebus.on('changeSite', (Code) => {
      site.value = getCookies('site')
    })
    onBeforeUnmount(() => {
      ebus.off('changeSite')
    })

    return {
      customerCode,
      dateFrom,
      dateTo,
      site,
      isAuthorised,
      checkInputAll,
      proSearch,
      tableHeight,
      tableWidth
    }
  }
})
</script>
