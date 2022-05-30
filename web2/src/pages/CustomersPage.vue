<!--
 * @Author         : Robert Huang<56649783@qq.com>
 * @Date           : 2022-04-08 09:36:21
 * @LastEditors    : Robert Huang<56649783@qq.com>
 * @LastEditTime   : 2022-05-30 12:41:15
 * @FilePath       : \web2\src\pages\CustomersPage.vue
 * @CopyRight      : Dedienne Aerospace China ZhuHai
-->
<template>
  <q-page>
    <ExceptionLottie :ErrorCode="403" v-if="!isAuthorised('GESSOH')" />
    <WaitInputLottieVue v-if="!customerCode && isAuthorised('GESSOH')" />

    <div class="row q-gutter-sm q-pa-sm" v-if="isAuthorised('GESSOH')">
      <QSelectAxiosVue
        option-label="CustomerName"
        option-value="CustomerCode"
        data-url="/Data/CustomerHelper"
        :label="$t('Search Your Customers (Code or Name)')"
        input-style="font-weight:bolder;font-size:25px;text-transform:uppercase"
        popup-content-style="font-weight:bold;font-size:25px"
        popup-content-class="text-secondary"
        class="col-grow"
        v-model="customerCode"
      />
      <q-input
        dense
        outlined
        debounce="1000"
        mask="date"
        type="date"
        class="col-3"
        :label="$t('From')"
        v-model="dateFrom"
      />
      <q-input
        dense
        outlined
        debounce="1000"
        mask="date"
        type="date"
        class="col-3"
        :label="$t('To')"
        v-model="dateTo"
      />
    </div>
    <QCardCustomerInfoVue
      :customerCode="customerCode"
      class="q-mx-sm q-pt-sm"
      v-if="customerCode"
    />
    <QMarkupTableCustomerOpenItemsVue
      :customerCode="customerCode"
      :dateFrom="dateFrom"
      :dateTo="dateTo"
      class="row q-mx-sm q-mt-sm col-grow"
      v-if="customerCode"
    />
    <div class="row q-mx-sm q-mt-sm" v-if="customerCode">
      <EchartCustomerOpenQtyVue
        :customerCode="customerCode"
        :dateFrom="dateFrom"
        :dateTo="dateTo"
        style="padding: 0px; height: 200px"
        class="col-6"
      />
      <EchartCustomerOpenAmountVue
        :customerCode="customerCode"
        :dateFrom="dateFrom"
        :dateTo="dateTo"
        style="padding: 0px; height: 200px"
        class="col-6"
      />
    </div>
    <div class="row q-mx-sm q-mt-sm" v-if="customerCode">
      <EchartCustomerTotalQtyVue
        :customerCode="customerCode"
        :dateFrom="dateFrom"
        :dateTo="dateTo"
        style="padding: 0px; height: 200px"
        class="col-6"
      />
      <EchartCustomerTotalAmountVue
        :customerCode="customerCode"
        :dateFrom="dateFrom"
        :dateTo="dateTo"
        style="padding: 0px; height: 200px"
        class="col-6"
      />
    </div>
    <div class="row q-mx-sm q-mt-sm" v-if="customerCode">
      <EchartCustomerDeliveryHistoryVue
        :customerCode="customerCode"
        :dateFrom="dateFrom"
        :dateTo="dateTo"
        style="padding: 0px; height: 200px"
        class="col-6"
      />
      <EchartCustomerDelayHistoryVue
        :customerCode="customerCode"
        :dateFrom="dateFrom"
        :dateTo="dateTo"
        style="padding: 0px; height: 200px"
        class="col-6"
      />
    </div>
  </q-page>
</template>

<script setup>
import { isAuthorised } from '@/assets/auth'
import QSelectAxiosVue from '@/components/.controls/QSelectAxios.vue'
import QCardCustomerInfoVue from '@/components/customers/QCardCustomerInfo.vue'
import QMarkupTableCustomerOpenItemsVue from '@/components/customers/QMarkupTableCustomerOpenItems.vue'
import EchartCustomerDelayHistoryVue from '@/components/echarts/EchartCustomerDelayHistory.vue'
import EchartCustomerDeliveryHistoryVue from '@/components/echarts/EchartCustomerDeliveryHistory.vue'
import EchartCustomerOpenAmountVue from '@/components/echarts/EchartCustomerOpenAmount.vue'
import EchartCustomerOpenQtyVue from '@/components/echarts/EchartCustomerOpenQty.vue'
import EchartCustomerTotalAmountVue from '@/components/echarts/EchartCustomerTotalAmount.vue'
import EchartCustomerTotalQtyVue from '@/components/echarts/EchartCustomerTotalQty.vue'
import ExceptionLottie from '@/components/lottie/ExceptionLottie.vue'
import WaitInputLottieVue from '@/components/lottie/WaitInputLottie.vue'
import { date } from 'quasar'
import { ref } from 'vue'

/* eslint-disable */
const props = defineProps({
  pageHeight: { type: Number, default: 0 /* not passing  */ }
})

// page vars
const customerCode = ref(null)
const { formatDate, addToDate } = date

const nowTimeStamp = Date.now()
const fromTimeStamp = addToDate(nowTimeStamp, { years: -3 })
const dateFrom = ref(formatDate(fromTimeStamp, 'YYYY-MM-DD'))
const dateTo = ref(formatDate(nowTimeStamp, 'YYYY-MM-DD'))
</script>
<style lang="scss" scoped></style>
