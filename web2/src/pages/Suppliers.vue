<!--  -->
<template>
  <q-page>
    <lottie-player
      src="/json/waiting-input.json"
      background="transparent"
      autoplay
      loop
      style="width: 600px; height: 600px; z-index: -1;"
      class="fixed-center"
      v-if="!supplierCode"
    />
    <div class="row  q-ml-md q-mr-md">
      <q-select-input
        option-label="SupplierName"
        option-value="SupplierCode"
        data-url="/Data/SupplierHelper?SupplierCodeOrName="
        emit-to="searchSupplier"
        :label="$t('Search Your Suppliers (Code or Name)')"
        popup-content-class="text-secondary"
        class="col-6 q-pr-md"
        :disable="!isAuthorised('GESPOH')"
      />
      <div class="col-3">
        <q-input
          v-model="dateFrom"
          debounce="1000"
          mask="date"
          :rules="['date']"
          :label="$t('From')"
          class="q-pr-md"
          :disable="!isAuthorised('GESPOH')"
        />
      </div>
      <div class="col-3">
        <q-input
          v-model="dateTo"
          debounce="1000"
          mask="date"
          :rules="['date']"
          :label="$t('To')"
          class="q-pl-md"
          :disable="!isAuthorised('GESPOH')"
        />
      </div>
    </div>
    <q-card-supplier-info
      :supplierCode="supplierCode"
      v-if="supplierCode"
    />
    <q-list class="row">
      <q-markup-table-supplier-open-items
        :supplierCode="supplierCode"
        :dateFrom="dateFrom"
        :dateTo="dateTo"
        class="col-12"
        v-show="isAuthorised('GESPOH')"
      />
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
  </q-page>
</template>

<script>
import { defineComponent, ref, onBeforeUnmount } from 'vue'
import { useQuasar, date } from 'quasar'

import { isAuthorised } from 'assets/auth'
import { ebus } from 'boot/ebus'
import '@lottiefiles/lottie-player'

import QSelectInput from 'src/components/QSelectInput.vue'
import QCardSupplierInfo from 'src/components/QCardSupplierInfo.vue'
import QMarkupTableSupplierOpenItems from 'src/components/QMarkupTableSupplierOpenItems.vue'
import EchartSupplierOpenQty from 'src/components/echarts/EchartSupplierOpenQty.vue'
import EchartSupplierTotalQty from 'src/components/echarts/EchartSupplierTotalQty.vue'
import EchartSupplierOpenAmount from 'src/components/echarts/EchartSupplierOpenAmount.vue'
import EchartSupplierTotalAmount from 'src/components/echarts/EchartSupplierTotalAmount.vue'
import EchartSupplierDeliveryHistory from 'src/components/echarts/EchartSupplierDeliveryHistory.vue'
import EchartSupplierDelayHistory from 'src/components/echarts/EchartSupplierDelayHistory.vue'

export default defineComponent({
  name: 'Suppliers',

  components: {
    QSelectInput,
    QCardSupplierInfo,
    QMarkupTableSupplierOpenItems,
    EchartSupplierOpenQty,
    EchartSupplierOpenAmount,
    EchartSupplierTotalQty,
    EchartSupplierTotalAmount,
    EchartSupplierDeliveryHistory,
    EchartSupplierDelayHistory
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

    const ddd = (val) => {
      return false
    }

    const update = (Code) => {
      supplierCode.value = Code
    }

    // event handing
    ebus.on('searchSupplier', (Code) => { update(Code) })
    onBeforeUnmount(() => { ebus.off('searchSupplier') })

    // return them to vue template
    return {
      isAuthorised,
      supplierCode,
      dateFrom,
      dateTo,
      ddd
    }
  }
})
</script>
<style lang='scss' scoped>
</style>
