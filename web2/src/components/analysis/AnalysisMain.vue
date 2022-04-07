<template>
  <q-tabs
    v-model="tab"
    dense
    align="left"
    class="text-grey"
    active-color="primary"
    indicator-color="primary"
    narrow-indicator
    :style="'height:' + tabHeaderHeight + 'px'"
  >
    <q-tab name="QuoteSalesCost" label="Quote-Sales-Cost" />
  </q-tabs>

  <q-separator />

  <q-tab-panels v-model="tab" keep-alive>
    <q-tab-panel name="QuoteSalesCost" style="padding: 0px">
      <quote-sales-cost
        :tabPanelHeight="tabPanelHeight"
      />
    </q-tab-panel>
  </q-tab-panels>
</template>

<script>
import { defineComponent, ref, onBeforeMount } from 'vue'
import { useQuasar } from 'quasar'
import { isAuthorised } from 'assets/auth'
import QuoteSalesCost from 'components/analysis/QuoteSalesCost.vue'

export default defineComponent({
  name: 'QTabsAnalysis',

  components: {
    QuoteSalesCost
  },

  setup(props, ctx) {
    const $q = useQuasar()
    const tab = ref('QuoteSalesCost')
    const tabPanelHeight = ref(600)
    const tabHeaderHeight = ref(36)

    onBeforeMount(() => {
      console.debug('onBeforeMount TabsStock')
      tabPanelHeight.value = $q.pageBodyHeight - tabHeaderHeight.value
      console.debug('tabPanelHeight.value:' + tabPanelHeight.value)
    })

    return {
      tab,
      isAuthorised,
      tabPanelHeight,
      tabHeaderHeight
    }
  }
})
</script>
<style lang="scss" scoped></style>
