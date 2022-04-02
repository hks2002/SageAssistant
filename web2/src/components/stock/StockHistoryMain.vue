<template>
  <q-card>
    <q-list class="row q-gutter-sm q-pa-sm">
      <q-input
        clearable
        outlined
        hide-hint
        hide-bottom-space
        debounce="1000"
        class="col"
        input-class="text-uppercase"
        hint="example: MS 856A NUT SCREW"
        :label="$t('Filter result by PN or Name')"
        v-model="PnOrName"
        @update:model-value="doUpdate"
      >
      </q-input>
      <q-input
        outlined
        hide-bottom-space
        debounce="1000"
        mask="date"
        type="date"
        class="col-3"
        :label="$t('From')"
        v-model="dateFrom"
        @update:model-value="doUpdate"
      />
      <q-input
        outlined
        hide-bottom-space
        debounce="1000"
        mask="date"
        type="date"
        class="col-3"
        :label="$t('To')"
        v-model="dateTo"
        @update:model-value="doUpdate"
      />
    </q-list>
    <q-list class="row q-gutter-sm q-px-sm">
      <q-markup-table-stock-history-vue
        :PnOrName="PnOrName"
        :dateFrom="dateFrom"
        :dateTo="dateTo"
        :style="{ height: tableHeight + 'px', width: tableWidth + 'px' }"
      />
    </q-list>
  </q-card>
</template>

<script>
import { defineComponent, ref, onMounted, onBeforeMount } from 'vue'
import { useQuasar, date } from 'quasar'
import { isAuthorised } from 'assets/auth'
import QMarkupTableStockHistoryVue from './QMarkupTableStockHistory.vue'

export default defineComponent({
  name: 'StockHistoryMain',
  components: {
    QMarkupTableStockHistoryVue
  },
  setup(props, ctx) {
    const $q = useQuasar()

    const tableHeight = ref(250)
    const tableWidth = ref(600)

    const { formatDate, addToDate } = date
    const nowTimeStamp = Date.now()
    const fromTimeStamp = addToDate(nowTimeStamp, { months: -1 })
    const dateFrom = ref(formatDate(fromTimeStamp, 'YYYY-MM-DD'))
    const dateTo = ref(formatDate(nowTimeStamp, 'YYYY-MM-DD'))

    const PnOrName = ref('')
    const doUpdate = () => {
      // it doesn't need actually.
    }

    // 92 is the input height
    onBeforeMount(() => {
      console.debug('onBeforeMount StockHistory')
      // should consider element margin/padding value
      tableWidth.value = $q.pageBodyWidth - 8 * 2
      tableHeight.value = $q.pageBodyHeight - 36 - 72 - 8
    })

    onMounted(() => {})

    return {
      tableHeight,
      tableWidth,
      PnOrName,
      dateTo,
      dateFrom,
      doUpdate,
      isAuthorised
    }
  }
})
</script>
<style lang="scss" scoped></style>
