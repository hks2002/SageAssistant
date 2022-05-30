<template>
  <!-- set height and width in parent -->
  <q-markup-table dense>
    <thead style="position: sticky; top: 0px; z-index: 1">
      <tr>
        <td :colspan="colspan" class="bg-teal text-h6 text-white shadow-2">
          Invoice Pay of customer {{ customerCode }} from {{ dateFrom }} to
          {{ dateTo }}
          <q-btn dense flat icon="fas fa-download" @click="download()" />
        </td>
      </tr>
      <tr class="bg-primary text-white">
        <th class="text-center text-caption">#</th>
        <th class="text-left">Site</th>
        <th class="text-left">Customer</th>
        <th class="text-left">Name</th>
        <th class="text-left">InvoiceNO</th>
        <th class="text-left">Currency</th>
        <th class="text-right">Amount</th>
        <th class="text-right">AmountLocal</th>
        <th class="text-right">Pay</th>
        <th class="text-right">PayLocal</th>
        <th class="text-left">OrderNO</th>
        <th class="text-center">CreateDate</th>
        <th class="text-center">DueDate</th>
        <th class="text-center">PayDate</th>
        <th class="text-left">Fapiao</th>
        <th class="text-left">CustRef</th>
        <th class="text-center">Status</th>
        <template v-if="proSearch">
          <th class="text-left">MatchedBy</th>
          <th class="text-left">PayNO</th>
          <th class="text-center">PayCurrency</th>
          <th class="text-right">PayInPayNO</th>
          <th class="text-left">Desc0</th>
          <th class="text-left">Desc1</th>
        </template>
      </tr>
    </thead>
    <tbody>
      <tr
        v-for="(item, index) in invoicePayItems"
        :key="index"
        :style="{ backgroundColor: item['Status'] !== 'Paid' ? '#ffe9e9' : '' }"
      >
        <td class="text-center">{{ index }}</td>
        <td>{{ item['Site'] }}</td>
        <td class="text-left">{{ item['Customer'] }}</td>
        <td class="text-left">{{ item['Name'] }}</td>
        <td class="text-left">{{ item['InvoiceNO'] }}</td>
        <td class="text-left">{{ item['Currency'] }}</td>
        <td class="text-right">{{ item['Amount'] }}</td>
        <td class="text-right">{{ item['AmountLocal'] }}</td>
        <td class="text-right">{{ item['Pay'] }}</td>
        <td class="text-right">{{ item['PayLocal'] }}</td>
        <td class="text-left">{{ item['OrderNO'] }}</td>
        <td class="text-center">{{ item['CreateDate'] }}</td>
        <td class="text-center">{{ item['DueDate'] }}</td>
        <td class="text-center">{{ item['PayDate'] }}</td>
        <td class="text-left">{{ item['Fapiao'] }}</td>
        <td class="text-left">{{ item['CustRef'] }}</td>
        <td class="text-center">{{ item['Status'] }}</td>
        <template v-if="proSearch">
          <td class="text-left">{{ item['MatchedBy'] }}</td>
          <td class="text-left">{{ item['PayNO'] }}</td>
          <td class="text-center">{{ item['PayCurrency'] }}</td>
          <td class="text-right">{{ item['PayInPayNO'] }}</td>
          <td class="text-left">{{ item['Desc0'] }}</td>
          <td class="text-left">{{ item['Desc1'] }}</td>
        </template>
      </tr>
    </tbody>
    <q-inner-loading :showing="showLoading">
      <q-spinner-hourglass size="50px" color="primary" />
    </q-inner-loading>
  </q-markup-table>
</template>

<script setup>
import { axiosGet } from '@/assets/axiosActions'
import { jsonToExcel } from 'assets/dataUtils'
import { onMounted, ref, watch } from 'vue'

const props = defineProps({
  customerCode: {
    type: String,
    require: false,
    default: null
  },
  dateFrom: {
    type: String,
    require: true
  },
  dateTo: {
    type: String,
    require: true
  },
  proSearch: {
    type: Boolean,
    require: false,
    default: false
  },
  site: {
    type: String,
    require: false,
    default: null
  }
})

// common vars
const showLoading = ref(false)

// components vars
const invoicePayItems = ref([])
const colspan = ref(17)

// actions
const doUpdate = () => {
  if (!props.customerCode) return

  showLoading.value = true

  const code = props.customerCode === '%%' ? '' : props.customerCode
  const proSuffix = props.proSearch ? 'Pro' : ''
  colspan.value = props.proSearch ? 23 : 17

  axiosGet(
    '/Data/FinancialInvoicePay' +
      proSuffix +
      '?Site=' +
      props.site +
      '&CustomerCode=' +
      code +
      '&DateFrom=' +
      props.dateFrom +
      '&DateTo=' +
      props.dateTo
  )
    .then((response) => {
      invoicePayItems.value = response
    })
    .finally(() => {
      showLoading.value = false
    })
}
const download = () => {
  const header = [
    'Site',
    'Customer',
    'Name',
    'InvoiceNO',
    'Currency',
    'Amount',
    'AmountLocal',
    'Pay',
    'PayLocal',
    'OrderNO',
    'CreateDate',
    'DueDate',
    'PayDate',
    'Fapiao',
    'CustRef',
    'Status'
  ]
  if (props.proSearch) {
    header.push('MathedBy')
    header.push('PayNO')
    header.push('PayCurrency')
    header.push('PayInPayNO')
    header.push('Desc0')
    header.push('Desc1')
  }
  jsonToExcel(
    header,
    invoicePayItems.value,
    props.customerCode + '-Invoice Pay'
  )
}

// events
onMounted(() => {
  doUpdate()
})

// Don't use watchEffect, it run before Mounted.
watch(
  () => [
    props.customerCode,
    props.proSearch,
    props.dateFrom,
    props.dateTo,
    props.site
  ],
  (...newAndold) => {
    // newAndold[1]:old
    // newAndold[0]:new
    console.debug('watch:' + newAndold[1] + ' ---> ' + newAndold[0])
    doUpdate()
  }
)
</script>
