<template>
  <q-page>
    <WaitInputLottieVue v-if="!accountNO && isAuthorised('GESSIH')" />
    <ExceptionLottieVue :ErrorCode="403" v-if="!isAuthorised('GESSIH')" />

    <div class="row q-gutter-sm q-px-sm q-pt-sm" v-if="isAuthorised('GESSIH')">
      <q-input
        v-model="accountNO"
        dense
        outlined
        debounce="1000"
        :label="$t('AccountNO')"
        hint="such as: 1001000 or 1001000,1001001,1001002"
        hide-hint
        hide-bottom-space
        input-style="font-weight:bolder;font-size:25px;text-transform:uppercase"
        class="col-grow"
      />
      <q-input
        v-model="year"
        dense
        outlined
        debounce="1000"
        :label="$t('Year')"
        class="col-1"
      />
      <q-toggle v-model="showBalance" dense label="Balance" class="col-1" />
      <q-toggle v-model="showDebit" dense label="Debit" class="col-1" />
      <q-toggle v-model="showCredit" dense label="Credit" class="col-1" />
      <q-toggle v-model="showMovement" dense label="Movement" class="col-1" />
    </div>
    <q-list class="q-pa-sm" v-if="accountNO">
      <div class="row" style="padding: 0px; height: 250px">
        <echart-fiancial-account-balance-vue
          :site="site"
          cat="B"
          :year="year"
          :accountNO="accountNO"
          class="col-3"
          v-if="showBalance"
        />
        <echart-fiancial-account-balance-vue
          :site="site"
          cat="D"
          :year="year"
          :accountNO="accountNO"
          class="col-3"
          v-if="showDebit"
        />
        <echart-fiancial-account-balance-vue
          :site="site"
          cat="C"
          :year="year"
          :accountNO="accountNO"
          class="col-3"
          v-if="showCredit"
        />
        <echart-fiancial-account-balance-vue
          :site="site"
          cat="M"
          :year="year"
          :accountNO="accountNO"
          class="col-3"
          v-if="showMovement"
        />
      </div>
      <q-markup-table-balance-vue
        :accountNO="accountNO"
        :year="year"
        :site="site"
        :showBalance="showBalance"
        :showCredit="showCredit"
        :showDebit="showDebit"
        :showMovement="showMovement"
        :style="{ height: tableHeight + 'px' }"
      />
    </q-list>
  </q-page>
</template>

<script setup>
import { isAuthorised } from '@/assets/auth'
import { ebus } from '@/assets/ebus'
import { getCookies } from '@/assets/storage'
import EchartFiancialAccountBalanceVue from '@/components/echarts/EchartAccountBalance.vue'
import QMarkupTableBalanceVue from '@/components/Financials/QMarkupTableBalance.vue'
import ExceptionLottieVue from '@/components/lottie/ExceptionLottie.vue'
import WaitInputLottieVue from '@/components/lottie/WaitInputLottie.vue'
import { date } from 'quasar'
import { computed, onBeforeUnmount, ref } from 'vue'

/* eslint-disable */
const props = defineProps({
  pageHeight: { type: Number, default: 0 /* not passing  */ }
})

// common vars
const site = ref(getCookies('site'))

// page vars
const accountNO = ref(null)
const nowTimeStamp = Date.now()
const formattedString = date.formatDate(
  nowTimeStamp,
  'YYYY-MM-DDTHH:mm:ss.SSSZ'
)
const year = ref(formattedString.substring(0, 4))

const showBalance = ref(true)
const showDebit = ref(true)
const showCredit = ref(true)
const showMovement = ref(true)

// computed vars
const tableHeight = computed(() => {
  /** 56: QSelect height, 16: padding height */
  return props.pageHeight - 56 - 8
})

// events
ebus.on('changeSite', () => {
  site.value = getCookies('site')
})

onBeforeUnmount(() => {
  ebus.off('changeSite')
})
</script>
