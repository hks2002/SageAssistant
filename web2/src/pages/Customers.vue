<!--  -->
<template>
  <q-page :key="timer">
    <vue3-lottie
      animationLink="/json/403.json"
      class="fixed-center"
      v-if="!isAuthorised('GESSOH')"
    />
    <div v-else>
      <div class="row q-gutter-sm q-pa-sm">
        <q-select-input
          option-label="CustomerName"
          option-value="CustomerCode"
          data-url="/Data/CustomerHelper?CustomerCodeOrName="
          emit-to="searchCustomer"
          :label="$t('Search Your Customers (Code or Name)')"
          popup-content-class="text-secondary"
          class="col-grow"
        />
        <div class="col-3">
          <q-input
            v-model="dateFrom"
            outlined
            debounce="1000"
            mask="date"
            type="date"
            :label="$t('From')"
          />
        </div>
        <div class="col-3">
          <q-input
            v-model="dateTo"
            outlined
            debounce="1000"
            mask="date"
            type="date"
            :label="$t('To')"
          />
        </div>
      </div>
      <Vue3Lottie
        animationLink="/json/waiting-input.json"
        :style="{ height: '500px' }"
        v-if="!customerCode"
      />
      <q-card-customer-info
        :customerCode="customerCode"
        v-if="customerCode"
        class="q-mx-sm"
      />
      <q-list class="row q-mx-sm q-mt-sm" v-if="customerCode">
        <q-markup-table-customer-open-items
          :customerCode="customerCode"
          :dateFrom="dateFrom"
          :dateTo="dateTo"
          class="col"
        />
      </q-list>
      <q-list class="row q-mx-sm q-mt-sm" v-if="customerCode">
        <echart-customer-open-qty
          :customerCode="customerCode"
          :dateFrom="dateFrom"
          :dateTo="dateTo"
          style="padding: 0px; height: 200px"
          class="col-6"
        />
        <echart-customer-open-amount
          :customerCode="customerCode"
          :dateFrom="dateFrom"
          :dateTo="dateTo"
          style="padding: 0px; height: 200px"
          class="col-6"
        />
      </q-list>
      <q-list class="row q-mx-sm q-mt-sm" v-if="customerCode">
        <echart-customer-total-qty
          :customerCode="customerCode"
          :dateFrom="dateFrom"
          :dateTo="dateTo"
          style="padding: 0px; height: 200px"
          class="col-6"
        />
        <echart-customer-total-amount
          :customerCode="customerCode"
          :dateFrom="dateFrom"
          :dateTo="dateTo"
          style="padding: 0px; height: 200px"
          class="col-6"
        />
      </q-list>
      <q-list class="row q-mx-sm q-mt-sm" v-if="customerCode">
        <echart-customer-delivery-history
          :customerCode="customerCode"
          :dateFrom="dateFrom"
          :dateTo="dateTo"
          style="padding: 0px; height: 200px"
          class="col-6"
        />
        <echart-customer-delay-history
          :customerCode="customerCode"
          :dateFrom="dateFrom"
          :dateTo="dateTo"
          style="padding: 0px; height: 200px"
          class="col-6"
        />
      </q-list>
    </div>
  </q-page>
</template>

<script>
import { defineComponent, ref, onBeforeUnmount } from 'vue'
import { useQuasar, date } from 'quasar'

import { isAuthorised } from 'assets/auth'
import { ebus } from 'boot/ebus'

import QSelectInput from 'components/.controls/QSelectInput.vue'
import QCardCustomerInfo from 'components/customers/QCardCustomerInfo.vue'
import QMarkupTableCustomerOpenItems from 'components/customers/QMarkupTableCustomerOpenItems.vue'
import EchartCustomerOpenQty from 'components/echarts/EchartCustomerOpenQty.vue'
import EchartCustomerTotalQty from 'components/echarts/EchartCustomerTotalQty.vue'
import EchartCustomerOpenAmount from 'components/echarts/EchartCustomerOpenAmount.vue'
import EchartCustomerTotalAmount from 'components/echarts/EchartCustomerTotalAmount.vue'
import EchartCustomerDeliveryHistory from 'components/echarts/EchartCustomerDeliveryHistory.vue'
import EchartCustomerDelayHistory from 'components/echarts/EchartCustomerDelayHistory.vue'
import { Vue3Lottie } from 'vue3-lottie'

export default defineComponent({
  name: 'Customers',
  components: {
    QSelectInput,
    QCardCustomerInfo,
    QMarkupTableCustomerOpenItems,
    EchartCustomerOpenQty,
    EchartCustomerOpenAmount,
    EchartCustomerTotalQty,
    EchartCustomerTotalAmount,
    EchartCustomerDeliveryHistory,
    EchartCustomerDelayHistory,
    Vue3Lottie
  },

  setup() {
    ebus.emit('closeLeftDrawer')
    ebus.emit('activePage', 'Customers')

    const $q = useQuasar()
    const { formatDate, addToDate } = date
    const customerCode = ref('')

    const nowTimeStamp = Date.now()
    const fromTimeStamp = addToDate(nowTimeStamp, { years: -3 })
    const dateFrom = ref(formatDate(fromTimeStamp, 'YYYY-MM-DD'))
    const dateTo = ref(formatDate(nowTimeStamp, 'YYYY-MM-DD'))

    $q.loadingBar.stop()
    ebus.emit('closeLeftDrawer')
    ebus.emit('activePage', 'Customers')

    const update = (Code) => {
      customerCode.value = Code
    }

    // event handing
    ebus.on('searchCustomer', (Code) => {
      update(Code)
    })
    onBeforeUnmount(() => {
      ebus.off('searchCustomer')
    })

    return {
      timer: new Date().getTime(),
      isAuthorised,
      customerCode,
      dateFrom,
      dateTo
    }
  }
})
</script>
<style lang="scss" scoped></style>
