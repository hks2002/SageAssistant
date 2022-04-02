<!--  -->
<template>
  <Vue3Lottie
    animationLink="/json/waiting-input.json"
    :height="600"
    :width="600"
    class="fixed-center"
    v-if="!pnRoot && isAuthorised('GESITM')"
  />
  <Vue3Lottie
    animationLink="/json/403.json"
    :height="600"
    :width="600"
    class="fixed-center"
    v-if="!isAuthorised('GESITM')"
  />
  <q-select-input
    option-label="PN"
    option-value="PNROOT"
    data-url="/Data/PNHelper?PnOrPnRoot="
    emit-to="searchPn"
    :label="$t('Search Your Product')"
    popup-content-class="text-secondary"
    class="q-pa-sm"
    :disable="!isAuthorised('GESITM')"
  />
  <div
    class="row q-gutter-sm q-pa-sm"
    style="height:200px;"
    v-if="pnRoot"
  >
    <q-card class="col-grow">
      <q-scroll-area style="padding:0px; height:200px;">
        <q-list-pn-list :pnRoot="pnRoot" />
      </q-scroll-area>
    </q-card>
    <q-card class="col-3">
      <q-scroll-area style="padding:0px; height:200px;">
        <echart-inventory-stock
          :pnRoot="pnRoot"
          style="padding:0px; height:200px;"
          v-show="isAuthorised('CONSSDE')"
        />
      </q-scroll-area>
    </q-card>
    <q-card class="col-4">
      <q-scroll-area style="padding:0px; height:200px;">
        <echart-delivery-duration
          :pnRoot="pnRoot"
          style="padding:0px; height:200px;"
          v-show="isAuthorised('GESSDH')"
        />
      </q-scroll-area>
    </q-card>
  </div>
  <div class="row q-mt-md q-pa-sm">
    <echart-sales-history
      :pnRoot="pnRoot"
      style="padding:0px; height:300px;"
      class="col-grow"
      v-show="isAuthorised('GESSOH')"
    />
  </div>
  <div class="row  q-pa-sm">
    <echart-quote-history
      :pnRoot="pnRoot"
      style="padding:0px; height:300px;"
      class="col-grow"
      v-show="isAuthorised('GESSQH')"
    />
  </div>
  <div class="row  q-pa-sm">
    <echart-cost-history
      :pnRoot="pnRoot"
      style="padding:0px; height:300px;"
      class="col-grow"
      v-show="isAuthorised('GESPOH')"
    />
  </div>
</template>

<script>
import { defineComponent, ref, onBeforeUnmount } from 'vue'
import { useQuasar } from 'quasar'
import { ebus } from 'boot/ebus'
import { isAuthorised } from 'assets/auth'
import { Vue3Lottie } from 'vue3-lottie'

import QSelectInput from 'components/.controls/QSelectInput.vue'
import EchartSalesHistory from 'components/echarts/EchartSalesHistory.vue'
import EchartQuoteHistory from 'components/echarts/EchartQuoteHistory.vue'
import EchartCostHistory from 'components/echarts/EchartCostHistory.vue'
import EchartInventoryStock from 'components/echarts/EchartInventoryStock.vue'
import EchartDeliveryDuration from 'components/echarts/EchartDeliveryDuration.vue'
import QListPnList from 'components/products/QListPnList.vue'

export default defineComponent({
  name: 'ProductsMain',
  components: {
    QSelectInput,
    EchartSalesHistory,
    EchartQuoteHistory,
    EchartCostHistory,
    EchartInventoryStock,
    EchartDeliveryDuration,
    QListPnList,
    Vue3Lottie
  },

  setup() {
    const $q = useQuasar()
    const pnRoot = ref('')

    $q.loadingBar.stop()
    ebus.emit('closeLeftDrawer')
    ebus.emit('activePage', 'Products')

    const update = (newPNROOT) => {
      pnRoot.value = newPNROOT
    }

    // event handing
    ebus.on('searchPn', (PNROOT) => {
      update(PNROOT)
    })
    onBeforeUnmount(() => {
      ebus.off('searchPn')
    })

    // return them to vue template
    return {
      isAuthorised,
      pnRoot
    }
  }
})
</script>
<style lang='scss' scoped>
</style>
