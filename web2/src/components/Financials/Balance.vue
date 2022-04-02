<template>
  <q-card>
    <!-- <Vue3Lottie
      animationLink="/json/waiting-input.json"
      :height="600"
      :width="600"
      class="fixed-center"
      v-if="!customerCode && isAuthorised('GESITM')"
      style="z-index: -1"
    /> -->
    <vue3-lottie
      animationLink="/json/403.json"
      :height="600"
      :width="600"
      class="fixed-center"
      v-if="!isAuthorised('GESSIH')"
    />
    <div class="row q-gutter-sm q-px-sm q-pt-sm">
      <q-input
        v-model="accountNO"
        outlined
        debounce="1000"
        :label="$t('AccountNO')"
        hint="such as: 1001000 or 1001000,1001001,1001002"
        hide-hint
        hide-bottom-space
        :disable="!isAuthorised('GESSIH')"
        class="col-grow"
      />
      <q-input
        v-model="year"
        outlined
        debounce="1000"
        :label="$t('Year')"
        :disable="!isAuthorised('GESSIH')"
        class="col-1"
      />
      <q-toggle v-model="showCredit" label="Credit" class="col-1" />
      <q-toggle v-model="showDebit" label="Debit" class="col-1" />
      <q-toggle v-model="showMovement" label="Movement" class="col-1" />
      <q-toggle v-model="showBalance" label="Balance" class="col-1" />
    </div>
    <q-list class="q-pa-sm">
      <q-markup-table-balance-vue
        :accountNO="accountNO"
        :year="year"
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
import { isAuthorised } from 'assets/auth'
import { Vue3Lottie } from 'vue3-lottie'
import QMarkupTableBalanceVue from './QMarkupTableBalance.vue'

export default defineComponent({
  name: 'Balance',

  components: {
    Vue3Lottie,
    QMarkupTableBalanceVue
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

    const showCredit = ref(true)
    const showDebit = ref(true)
    const showMovement = ref(true)
    const showBalance = ref(true)

    const tableHeight = ref(250)
    const tableWidth = ref(600)

    const update = () => {
      customerCode.value = Code
    }

    onBeforeMount(() => {
      console.debug('onBeforeMount Balance')
      // should consider element margin/padding value
      tableWidth.value = $q.pageBodyWidth - 8 * 2
      tableHeight.value = $q.pageBodyHeight - 36 - 72 - 8
    })

    // event handing
    // ebus.on('searchBalance', (Code) => {
    //  update(Code)
    // })
    // onBeforeUnmount(() => {
    //  ebus.off('searchBalance')
    // })

    return {
      accountNO,
      year,
      showCredit,
      showDebit,
      showBalance,
      showMovement,
      update,
      isAuthorised,
      tableHeight,
      tableWidth
    }
  }
})
</script>
