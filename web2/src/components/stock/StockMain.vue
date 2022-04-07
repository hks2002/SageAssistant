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
    <q-tab name="Count" label="Count" />
    <q-tab name="History" label="History" />
  </q-tabs>

  <q-separator />

  <q-tab-panels v-model="tab" animated keep-alive>
    <q-tab-panel name="Count" style="padding: 0px">
      <stock-summary-main />
    </q-tab-panel>
    <q-tab-panel name="History" style="padding: 0px">
      <stock-history-main />
    </q-tab-panel>
  </q-tab-panels>
</template>

<script>
import { defineComponent, ref, onBeforeMount, onMounted } from 'vue'
import { useQuasar } from 'quasar'
import { ebus } from 'boot/ebus'
import { getCookies } from 'assets/storage'
import { isAuthorised } from 'assets/auth'
import StockSummaryMain from 'src/components/stock/StockSummaryMain.vue'
import StockHistoryMain from 'src/components/stock/StockHistoryMain.vue'

export default defineComponent({
  name: 'StockMain',

  components: {
    StockSummaryMain,
    StockHistoryMain
  },

  setup(props, ctx) {
    const $q = useQuasar()
    const tab = ref('Count')
    const site = ref('')
    site.value = getCookies('site')

    onBeforeMount(() => {
      console.debug('onBeforeMount TabsStock')
      // tabPanelHeight.value = $q.pageBodyHeight - tabHeaderHeight.value
    })

    onMounted(() => {
      console.debug('onMounted TabsStock')
    })

    ebus.emit('closeLeftDrawer')
    ebus.emit('activePage', 'Stock')

    return {
      tab,
      isAuthorised
    }
  }
})
</script>
<style lang="scss" scoped></style>
