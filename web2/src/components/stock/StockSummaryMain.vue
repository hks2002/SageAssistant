<template>
  <vue3-lottie
    animationLink="/json/403.json"
    v-if="!isAuthorised('CONSSAR')"
    class="fixed-center"
  />
  <q-card class="row q-gutter-sm q-pa-sm" v-else>
    <q-input
      :label="
        $t(
          'Input PN (support supplier\'s PN also) to filter-----Click PN to see its history'
        )
      "
      clearable
      outlined
      hide-hint
      hide-bottom-space
      debounce="1000"
      class="col-12"
      hint="All special character (',' '.' '/' '\' '-' '_') will be ignored to match."
      input-class="text-uppercase"
      v-model="PNfilter"
      @update:model-value="doFilterPN"
    >
    </q-input>
    <q-markup-table-stock-summary-vue
      :PNfilter="PNfilter"
      :style="{ height: tableHeight + 'px', width: tableWidth + 'px' }"
    />
  </q-card>
</template>

<script setup>
import { ref, onMounted, onBeforeMount } from 'vue'
import { useQuasar } from 'quasar'
import { isAuthorised } from 'assets/auth'
import QMarkupTableStockSummaryVue from './QMarkupTableStockSummary.vue'

const $q = useQuasar()

const PNfilter = ref('')

const tableHeight = ref(250)
const tableWidth = ref(600)

onBeforeMount(() => {
  console.debug('onBeforeMount StockSummary')
  // should consider element margin/padding value
  tableWidth.value = $q.pageBodyWidth - 8 * 2
  tableHeight.value = $q.pageBodyHeight - 36 - 72 - 8
})
onMounted(() => {
  console.debug('onMounted StockSummary')
})
</script>
<style lang="sass" scoped></style>
