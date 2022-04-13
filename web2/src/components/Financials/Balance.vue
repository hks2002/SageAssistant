<template>
  <vue3-lottie
    animationLink="/json/403.json"
    v-if="!isAuthorised('GESSIH')"
    class="fixed-center"
  />
  <q-card v-else>
    <div class="row q-gutter-sm q-px-sm q-pt-sm">
      <q-input
        v-model="accountNO"
        outlined
        debounce="1000"
        :label="$t('AccountNO')"
        hint="such as: 1001000 or 1001000,1001001,1001002"
        hide-hint
        hide-bottom-space
        class="col-grow"
      />
      <q-input
        v-model="year"
        outlined
        debounce="1000"
        :label="$t('Year')"
        class="col-1"
      />
      <q-toggle v-model="showBalance" label="Balance" class="col-1" />
      <q-toggle v-model="showDebit" label="Debit" class="col-1" />
      <q-toggle v-model="showCredit" label="Credit" class="col-1" />
      <q-toggle v-model="showMovement" label="Movement" class="col-1" />
    </div>
    <Vue3Lottie
      animationLink="/json/waiting-input.json"
      :style="{ height: tableHeight + 250 + 'px', width: tableWidth + 'px' }"
      v-if="!accountNO"
    />
    <q-list class="q-pa-sm" v-else>
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
        :style="{ height: tableHeight + 'px', width: tableWidth + 'px' }"
      />
    </q-list>
  </q-card>
</template>

<script>
import { defineComponent, ref, onBeforeMount, onBeforeUnmount } from 'vue'
import { useQuasar, date } from 'quasar'
import { ebus } from 'boot/ebus'
import { getCookies } from 'src/assets/storage'
import { isAuthorised } from 'assets/auth'
import { Vue3Lottie } from 'vue3-lottie'
import QMarkupTableBalanceVue from './QMarkupTableBalance.vue'
import EchartFiancialAccountBalanceVue from 'src/components/echarts/EchartAccountBalance.vue'

export default defineComponent({
  name: 'Balance',

  components: {
    Vue3Lottie,
    QMarkupTableBalanceVue,
    EchartFiancialAccountBalanceVue
  },

  setup(props, ctx) {
    const $q = useQuasar()
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

    const tableHeight = ref(250)
    const tableWidth = ref(600)

    const site = ref(getCookies('site'))

    onBeforeMount(() => {
      console.debug('onBeforeMount Balance')
      // should consider element margin/padding value
      tableWidth.value = $q.pageBodyWidth - 8 * 2
      tableHeight.value = $q.pageBodyHeight - 36 - 72 - 8 - 250
    })

    // event handing
    ebus.on('changeSite', (Code) => {
      site.value = getCookies('site')
    })
    onBeforeUnmount(() => {
      ebus.off('changeSite')
    })

    return {
      accountNO,
      year,
      site,
      showCredit,
      showDebit,
      showBalance,
      showMovement,
      isAuthorised,
      tableHeight,
      tableWidth
    }
  }
})
</script>
