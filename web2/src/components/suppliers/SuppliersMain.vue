<!--  -->
<template>
  <vue3-lottie
    animationLink="/json/waiting-input.json"
    :height="600"
    :width="600"
    class="fixed-center"
    v-if="!supplierCode && isAuthorised('GESPOH')"
  />
  <vue3-lottie
    animationLink="/json/403.json"
    :height="600"
    :width="600"
    class="fixed-center"
    v-if="!isAuthorised('GESPOH')"
  />
  <div class="row q-gutter-sm q-pa-sm">
    <q-select-input
      option-label="SupplierName"
      option-value="SupplierCode"
      data-url="/Data/SupplierHelper?SupplierCodeOrName="
      emit-to="searchSupplier"
      :label="$t('Search Your Suppliers (Code or Name)')"
      popup-content-class="text-secondary"
      class="col-grow"
      :disable="!isAuthorised('GESPOH')"
    />
    <div class="col-3">
      <q-input
        v-model="dateFrom"
        outlined
        debounce="1000"
        mask="date"
        type="date"
        :label="$t('From')"
        :disable="!isAuthorised('GESPOH')"
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
        :disable="!isAuthorised('GESPOH')"
      />
    </div>
  </div>
  <q-card-supplier-info
    :supplierCode="supplierCode"
    v-if="supplierCode"
    class="q-mx-sm"
  />
  <q-list class="row q-mx-sm q-mt-sm">
    <q-markup-table-supplier-open-items
      :supplierCode="supplierCode"
      :dateFrom="dateFrom"
      :dateTo="dateTo"
      class="col"
      v-show="isAuthorised('GESPOH')"
    />
  </q-list>
  <q-list class="row q-mx-sm q-mt-sm">
    <echart-supplier-open-qty
      :supplierCode="supplierCode"
      :dateFrom="dateFrom"
      :dateTo="dateTo"
      style="padding:0px; height:200px;"
      class="col-6"
      v-show="isAuthorised('GESPOH')"
    />
    <echart-supplier-open-amount
      :supplierCode="supplierCode"
      :dateFrom="dateFrom"
      :dateTo="dateTo"
      style="padding:0px; height:200px;"
      class="col-6"
      v-show="isAuthorised('GESPOH')"
    />
  </q-list>
  <q-list class="row q-mx-sm q-mt-sm">
    <echart-supplier-total-qty
      :supplierCode="supplierCode"
      :dateFrom="dateFrom"
      :dateTo="dateTo"
      style="padding:0px; height:200px;"
      class="col-6"
      v-show="isAuthorised('GESPOH')"
    />
    <echart-supplier-total-amount
      :supplierCode="supplierCode"
      :dateFrom="dateFrom"
      :dateTo="dateTo"
      style="padding:0px; height:200px;"
      class="col-6"
      v-show="isAuthorised('GESPOH')"
    />
  </q-list>
  <q-list class="row q-mx-sm q-mt-sm">
    <echart-supplier-delivery-history
      :supplierCode="supplierCode"
      :dateFrom="dateFrom"
      :dateTo="dateTo"
      style="padding:0px; height:200px;"
      class="col-6"
      v-show="isAuthorised('GESPOH')"
    />
    <echart-supplier-delay-history
      :supplierCode="supplierCode"
      :dateFrom="dateFrom"
      :dateTo="dateTo"
      style="padding:0px; height:200px;"
      class="col-6"
      v-show="isAuthorised('GESPOH')"
    />
  </q-list>
</template>

<script>
import { defineComponent, ref, onBeforeUnmount } from 'vue'
import { useQuasar, date } from 'quasar'

import { isAuthorised } from 'assets/auth'
import { ebus } from 'boot/ebus'

import QSelectInput from 'components/.controls/QSelectInput.vue'
import QCardSupplierInfo from 'components/suppliers/QCardSupplierInfo.vue'
import QMarkupTableSupplierOpenItems from 'components/suppliers/QMarkupTableSupplierOpenItems.vue'
import EchartSupplierOpenQty from 'components/echarts/EchartSupplierOpenQty.vue'
import EchartSupplierTotalQty from 'components/echarts/EchartSupplierTotalQty.vue'
import EchartSupplierOpenAmount from 'components/echarts/EchartSupplierOpenAmount.vue'
import EchartSupplierTotalAmount from 'components/echarts/EchartSupplierTotalAmount.vue'
import EchartSupplierDeliveryHistory from 'components/echarts/EchartSupplierDeliveryHistory.vue'
import EchartSupplierDelayHistory from 'components/echarts/EchartSupplierDelayHistory.vue'
import { Vue3Lottie } from 'vue3-lottie'

export default defineComponent({
  name: 'SuppliersMain',

  components: {
    QSelectInput,
    QCardSupplierInfo,
    QMarkupTableSupplierOpenItems,
    EchartSupplierOpenQty,
    EchartSupplierOpenAmount,
    EchartSupplierTotalQty,
    EchartSupplierTotalAmount,
    EchartSupplierDeliveryHistory,
    EchartSupplierDelayHistory,
    Vue3Lottie
  },

  setup() {
    const $q = useQuasar()
    const { formatDate, addToDate } = date
    const supplierCode = ref('')

    const nowTimeStamp = Date.now()
    const fromTimeStamp = addToDate(nowTimeStamp, { years: -3 })
    const dateFrom = ref(formatDate(fromTimeStamp, 'YYYY-MM-DD'))
    const dateTo = ref(formatDate(nowTimeStamp, 'YYYY-MM-DD'))

    $q.loadingBar.stop()
    ebus.emit('closeLeftDrawer')
    ebus.emit('activePage', 'Suppliers')

    const update = (Code) => {
      supplierCode.value = Code
    }

    // event handing
    ebus.on('searchSupplier', (Code) => {
      update(Code)
    })
    onBeforeUnmount(() => {
      ebus.off('searchSupplier')
    })

    // return them to vue template
    return {
      isAuthorised,
      supplierCode,
      dateFrom,
      dateTo
    }
  }
})
</script>
<style lang='scss' scoped>
</style>
