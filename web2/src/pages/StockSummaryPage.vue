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
        class="col-grow"
        :label="
          $t(
            'Input PN (support supplier\'s PN also) to filter-----Click PN to see its history'
          )
        "
        :hint="
          $t(
            'All special character (\',\' \'.\' \'/\' \'-\' \'_\') will be ignored to match.'
          )
        "
        input-class="text-uppercase"
        v-model="PNfilter"
      >
      </q-input>
    </q-list>
    <q-list
      class="row q-gutter-sm q-pt-sm q-px-sm"
      v-if="isAuthorised('CONSSAR')"
    >
      <QMarkupTableStockSummaryVue
        :PNfilter="PNfilter"
        :style="{ height: tableHeight + 'px' }"
      />
    </q-list>
  </q-page>
</template>

<script setup>
import { isAuthorised } from '@/assets/auth'
import QMarkupTableStockSummaryVue from '@/components/stock/QMarkupTableStockSummary'
import ExceptionLottie from '@/components/lottie/ExceptionLottie.vue'
import { useQuasar } from 'quasar'
import { computed, ref } from 'vue'

/* eslint-disable */
const props = defineProps({
  pageHeight: { type: Number, default: 0 /* not passing  */ }
})

// common vars
const $q = useQuasar()

// page vars
const PNfilter = ref('')

// computed vars
const tableHeight = computed(() => {
  // should consider element margin/padding value
  return props.pageHeight - 36 - 36
})
</script>
<style lang="sass" scoped></style>
