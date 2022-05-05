<!--  -->
<template>
  <q-page :key="timer">
    <Vue3Lottie
      animationLink="/json/403.json"
      class="fixed-center"
      v-if="!isAuthorised('GESPOH')"
    />
    <div v-else>
      <div class="row q-gutter-sm q-pa-sm">
        <QSelectInput
          option-label="SupplierName"
          option-value="SupplierCode"
          data-url="/Data/SupplierHelper?SupplierCodeOrName="
          emit-to="searchSupplier"
          :label="$t('Search Your Suppliers (Code or Name)')"
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
        v-if="!supplierCode"
      />
      <q-card-supplier-info
        :supplierCode="supplierCode"
        v-if="supplierCode"
        class="q-mx-sm"
      />
      <q-list class="row q-mx-sm q-mt-sm" v-if="supplierCode">
        <q-markup-table-supplier-open-items
          :supplierCode="supplierCode"
          :dateFrom="dateFrom"
          :dateTo="dateTo"
          class="col"
        />
      </q-list>
      <q-list class="row q-mx-sm q-mt-sm" v-if="supplierCode">
        <echart-supplier-open-qty
          :supplierCode="supplierCode"
          :dateFrom="dateFrom"
          :dateTo="dateTo"
          style="padding: 0px; height: 200px"
          class="col-6"
        />
        <echart-supplier-open-amount
          :supplierCode="supplierCode"
          :dateFrom="dateFrom"
          :dateTo="dateTo"
          style="padding: 0px; height: 200px"
          class="col-6"
        />
      </q-list>
      <q-list class="row q-mx-sm q-mt-sm" v-if="supplierCode">
        <echart-supplier-total-qty
          :supplierCode="supplierCode"
          :dateFrom="dateFrom"
          :dateTo="dateTo"
          style="padding: 0px; height: 200px"
          class="col-6"
        />
        <echart-supplier-total-amount
          :supplierCode="supplierCode"
          :dateFrom="dateFrom"
          :dateTo="dateTo"
          style="padding: 0px; height: 200px"
          class="col-6"
        />
      </q-list>
      <q-list class="row q-mx-sm q-mt-sm" v-if="supplierCode">
        <echart-supplier-delivery-history
          :supplierCode="supplierCode"
          :dateFrom="dateFrom"
          :dateTo="dateTo"
          style="padding: 0px; height: 200px"
          class="col-6"
        />
        <echart-supplier-delay-history
          :supplierCode="supplierCode"
          :dateFrom="dateFrom"
          :dateTo="dateTo"
          style="padding: 0px; height: 200px"
          class="col-6"
        />
      </q-list>
    </div>
  </q-page>
</template>

<script setup>
import { ref, onBeforeUnmount } from 'vue'
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

ebus.emit('closeLeftDrawer')
ebus.emit('activePage', 'Suppliers')
const timer = new Date().getTime()

const $q = useQuasar()
const { formatDate, addToDate } = date
const supplierCode = ref('')

const nowTimeStamp = Date.now()
const fromTimeStamp = addToDate(nowTimeStamp, { years: -3 })
const dateFrom = ref(formatDate(fromTimeStamp, 'YYYY-MM-DD'))
const dateTo = ref(formatDate(nowTimeStamp, 'YYYY-MM-DD'))

$q.loadingBar.stop()

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
</script>
<style lang="scss" scoped></style>
