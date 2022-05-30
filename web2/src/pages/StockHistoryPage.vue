<template>
  <q-page>
    <ExceptionLottie :ErrorCode="403" v-if="!isAuthorised('CONSSAR')" />
    <q-list
      class="row q-gutter-sm q-pt-sm q-px-sm"
      v-if="isAuthorised('CONSSAR')"
    >
      <q-input
        dense
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
        dense
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
        dense
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
    <q-list class="row q-gutter-sm q-pa-sm" v-if="isAuthorised('CONSSAR')">
      <QMarkupTableStockHistoryVue
        :PnOrName="PnOrName"
        :dateFrom="dateFrom"
        :dateTo="dateTo"
        :style="{ height: tableHeight + 'px' }"
      />
    </q-list>
  </q-page>
</template>

<script setup>
import { isAuthorised } from '@/assets/auth'
import ExceptionLottie from '@/components/lottie/ExceptionLottie.vue'
import QMarkupTableStockHistoryVue from '@/components/stock/QMarkupTableStockHistory'
import { date, useQuasar } from 'quasar'
import { computed, ref } from 'vue'

/* eslint-disable */
const props = defineProps({
  pageHeight: { type: Number, default: 0 /* not passing  */ }
})

// common vars
const $q = useQuasar()

// page vars
const PnOrName = ref('')
const { formatDate, addToDate } = date
const nowTimeStamp = Date.now()
const fromTimeStamp = addToDate(nowTimeStamp, { months: -1 })
const dateFrom = ref(formatDate(fromTimeStamp, 'YYYY-MM-DD'))
const dateTo = ref(formatDate(nowTimeStamp, 'YYYY-MM-DD'))

// computed vars
const tableHeight = computed(() => {
  // should consider element margin/padding value
  return props.pageHeight - 36 - 36
})

// actions
const doUpdate = () => {
  // it doesn't need actually.
}
</script>
<style lang="scss" scoped></style>
