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
      v-if="!pnRoot"
    />
    <q-select-input
      option-label="PN"
      option-value="PNROOT"
      data-url="/Data/PNHelper?PnOrPnRoot="
      emit-to="searchPn"
      :label="$t('Search Your Productions')"
      popup-content-class="text-secondary"
      class="q-ml-md q-mr-md"
    />
    <q-list
      class="row"
      style="padding:0px; height:200px;"
      v-if="pnRoot"
    >
      <div class="col-4">
        <q-card class="q-ma-md">
          <q-scroll-area style="padding:0px; height:200px;">
            <q-list-pn-list :pnRoot="pnRoot" />
          </q-scroll-area>
        </q-card>
      </div>
      <div class="col-4">
        <q-card class="q-ma-md">
          <echart-inventory-stock
            :pnRoot="pnRoot"
            style="padding:0px; height:200px;"
          />
        </q-card>
      </div>
      <div class="col-4">
        <q-card class="q-ma-md">
          <echart-delivery-duration
            :pnRoot="pnRoot"
            style="padding:0px; height:200px;"
          />
        </q-card>
      </div>

      <echart-sales-history
        :pnRoot="pnRoot"
        style="padding:0px; height:350px;"
        class="col-12"
      />
      <echart-quote-history
        :pnRoot="pnRoot"
        style="padding:0px; height:350px;"
        class="col-12"
      />
      <echart-cost-history
        :pnRoot="pnRoot"
        style="padding:0px; height:350px;"
        class="col-12"
      />
    </q-list>
  </q-page>
</template>

<script>
import { defineComponent, ref, onBeforeUnmount } from 'vue'
import { useQuasar } from 'quasar'
import { ebus } from 'boot/ebus'
import '@lottiefiles/lottie-player'

import QSelectInput from 'components/QSelectInput.vue'
import EchartSalesHistory from 'src/components/echarts/EchartSalesHistory.vue'
import EchartQuoteHistory from 'src/components/echarts/EchartQuoteHistory.vue'
import EchartCostHistory from 'src/components/echarts/EchartCostHistory.vue'
import EchartInventoryStock from 'src/components/echarts/EchartInventoryStock.vue'
import EchartDeliveryDuration from 'src/components/echarts/EchartDeliveryDuration.vue'
import QListPnList from 'src/components/QListPnList.vue'

export default defineComponent({
  name: 'Productions',

  components: {
    QSelectInput,
    EchartSalesHistory,
    EchartQuoteHistory,
    EchartCostHistory,
    EchartInventoryStock,
    EchartDeliveryDuration,
    QListPnList
  },

  setup() {
    const $q = useQuasar()
    const pnRoot = ref('')

    $q.loadingBar.stop()
    ebus.emit('closeLeftDrawer')
    ebus.emit('activePage', 'Productions')

    const update = (newPNROOT) => {
      pnRoot.value = newPNROOT
    }

    // event handing
    ebus.on('searchPn', (PNROOT) => {
      update(PNROOT)
    })
    onBeforeUnmount(() => { ebus.off('searchPn') })

    // return them to vue template
    return {
      pnRoot
    }
  }
})
</script>
<style lang='scss' scoped>
</style>
