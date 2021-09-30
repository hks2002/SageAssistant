<template>
  <q-page :key="timer">
    <q-tabs
      v-model="tab"
      dense
      align="left"
      class="text-grey"
      active-color="primary"
      indicator-color="primary"
      narrow-indicator
    >
      <q-tab
        name="Count"
        label="Count"
      />
      <q-tab
        name="History"
        label="History"
      />
    </q-tabs>

    <q-separator />

    <q-tab-panels
      v-model="tab"
      animated
      keep-alive
    >
      <q-tab-panel
        name="Count"
        style="padding:0px"
      >
        <stock-summary v-if="isAuthorised('CONSSAR')" />
        <error-403 v-else />
      </q-tab-panel>
      <q-tab-panel
        name="History"
        style="padding:0px"
      >
        <stock-history v-if="isAuthorised('CONSSAR')" />
        <error-403 v-else />
      </q-tab-panel>
    </q-tab-panels>
  </q-page>
</template>

<script>
import { defineComponent, ref, onBeforeUnmount } from 'vue'
import { ebus } from 'boot/ebus'
import { getCookies } from 'assets/storage'
import { isAuthorised } from 'assets/auth'
import StockSummary from 'src/components/StockSummary.vue'
import StockHistory from 'src/components/StockHistory.vue'
import Error403 from 'pages/Error403.vue'

export default defineComponent({
  name: 'Stock',

  components: {
    StockSummary,
    StockHistory,
    Error403
  },

  setup(props, ctx) {
    const tab = ref('Count')
    const site = ref('')
    site.value = getCookies('site')

    ebus.emit('closeLeftDrawer')
    ebus.emit('activePage', 'Stock')

    return {
      tab,
      timer: new Date().getTime(),
      isAuthorised
    }
  }
})
</script>
<style lang='scss' scoped>

</style>
