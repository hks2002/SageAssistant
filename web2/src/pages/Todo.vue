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
        name="Delivery"
        label="Delivery"
      />
      <q-tab
        name="Receive"
        label="Receive"
      />
      <q-tab
        name="NewOrder"
        label="NewOrder"
      />
      <q-tab
        name="NewBom"
        label="NewBom"
      />
      <q-tab
        name="Orphan-WO"
        label="Orphan-WO"
      />
    </q-tabs>

    <q-separator />

    <q-tab-panels
      v-model="tab"
      animated
      keep-alive
    >
      <q-tab-panel
        name="Delivery"
        style="padding:0px"
      >
        <echart-todo-delivery
          :site="site"
          :style="tabHeight"
          v-if="isAuthorised('GESPOH') || isAuthorised('GESSOH')"
        />
        <error-403 v-else />
      </q-tab-panel>

      <q-tab-panel
        name="Receive"
        style="padding:0px"
      >
        <echart-todo-receive
          :site="site"
          :style="tabHeight"
          v-if="isAuthorised('GESPOH') || isAuthorised('GESSOH')"
        />
        <error-403 v-else />
      </q-tab-panel>

      <q-tab-panel
        name="NewOrder"
        style="padding:0px"
      >
        <echart-todo-deal-with-order-line
          :site="site"
          :style="tabHeight"
          v-if="isAuthorised('GESPOH') || isAuthorised('GESSOH')"
        />
        <error-403 v-else />
      </q-tab-panel>
      <q-tab-panel
        name="NewBom"
        style="padding:0px"
      >
        <echart-todo-purchase-bom
          :site="site"
          :style="tabHeight"
          v-if="isAuthorised('GESPOH') || isAuthorised('GESSOH')"
        />
        <error-403 v-else />
      </q-tab-panel>
      <q-tab-panel
        name="Orphan-WO"
        style="padding:0px"
      >
        <echart-todo-closed-w-o
          :site="site"
          :style="tabHeight"
          v-if="isAuthorised('GESMFG')"
        />
        <error-403 v-else />
      </q-tab-panel>
    </q-tab-panels>
  </q-page>
</template>

<script>
import { defineComponent, ref, onBeforeUnmount } from 'vue'
import { useQuasar } from 'quasar'
import { getCookies } from 'assets/storage'
import { ebus } from 'boot/ebus'
import { isAuthorised } from 'assets/auth'

import EchartTodoDelivery from 'components/echarts/EchartTodoDelivery.vue'
import EchartTodoReceive from 'components/echarts/EchartTodoReceive.vue'
import EchartTodoPurchaseBom from 'components/echarts/EchartTodoPurchaseBom.vue'
import EchartTodoDealWithOrderLine from 'components/echarts/EchartTodoDealWithOrderLine.vue'
import EchartTodoClosedWO from 'components/echarts/EchartTodoClosedWO.vue'
import Error403 from 'pages/Error403.vue'

export default defineComponent({
  name: 'Todo',

  components: {
    EchartTodoDelivery,
    EchartTodoReceive,
    EchartTodoPurchaseBom,
    EchartTodoDealWithOrderLine,
    EchartTodoClosedWO,
    Error403
  },

  setup() {
    const $q = useQuasar()
    const tab = ref('Delivery')
    const site = ref('')
    site.value = getCookies('site')
    // without pageheader, pagefooter, tabheader
    const contentHeight = $q.screen.height - 40 - 36 - 18
    ebus.emit('closeLeftDrawer')
    ebus.emit('activePage', 'Todo')

    // event handing
    ebus.on('changeSite', (newSite) => {
      site.value = newSite
    })
    onBeforeUnmount(() => { ebus.off('changeSite') })

    return {
      tab,
      tabHeight: 'padding:0px; height:' + contentHeight + 'px',
      site,
      timer: new Date().getTime(),
      isAuthorised
    }
  }
})
</script>
<style lang='scss' scoped>

</style>
