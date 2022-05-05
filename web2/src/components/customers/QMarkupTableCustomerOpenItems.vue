<template>
  <q-markup-table
    dense
    v-if="customerOpenItems.length > 0"
    style="height: 250px"
  >
    <thead style="position: sticky; top: 0px; z-index: 1">
      <tr>
        <td colspan="11" class="bg-teal text-h6 text-white shadow-2">
          OpenItems(All-{{ customerOpenItems.length }})
          <q-btn dense flat icon="fas fa-download" @click="download()" />
        </td>
      </tr>
      <tr class="bg-primary text-white">
        <th class="text-center text-caption">#</th>
        <th class="text-left">Site</th>
        <th class="text-left">CustomerCode</th>
        <th class="text-left">OrderNO</th>
        <th class="text-left">ProjectNO</th>
        <th class="text-left">PN</th>
        <th class="text-left">Description</th>
        <th class="text-center">ShipDate</th>
        <th class="text-center">DemandDate</th>
        <th class="text-center">OrderDate</th>
        <th class="text-center">DaysDelay</th>
      </tr>
    </thead>
    <tbody>
      <tr v-for="(item, index) in customerOpenItems" :key="index">
        <td class="text-center">{{ index }}</td>
        <td>{{ item['Site'] }}</td>
        <td>{{ item['CustomerCode'] }}</td>
        <td>{{ item['OrderNO'] }}</td>
        <td>{{ item['ProjectNO'] }}</td>
        <td>{{ item['PN'] }}</td>
        <td>{{ item['Description'] }}</td>
        <td class="text-center">{{ item['ShipDate'] }}</td>
        <td class="text-center">{{ item['DemandDate'] }}</td>
        <td class="text-center">{{ item['OrderDate'] }}</td>
        <td class="text-center">{{ item['DaysDelay'] }}</td>
      </tr>
    </tbody>
  </q-markup-table>
</template>

<script setup>
import { defineComponent, onMounted, ref, watch } from 'vue'
import { notifyError } from 'assets/common'
import { axios } from 'boot/axios'
import { jsonToExcel } from 'assets/dataUtils'

const props = defineProps({
  customerCode: String,
  dateFrom: String,
  dateTo: String
})

const customerOpenItems = ref([])
const showLoading = ref(false)

const doUpdate = () => {
  showLoading.value = true

  axios
    .get(
      '/Data/CustomerOpenItems?CustomerCode=' +
        props.customerCode +
        '&DateFrom=' +
        props.dateFrom +
        '&DateTo=' +
        props.dateTo
    )
    .then((response) => {
      customerOpenItems.value = response.data
    })
    .catch((e) => {
      console.error(e)
      notifyError('Loading Customer Open Items Failed!')
    })
    .finally(() => {
      showLoading.value = false
    })
}
const download = () => {
  const header = [
    'Site',
    'CustomerCode',
    'OrderNO',
    'ProjectNO',
    'PN',
    'Description',
    'ShipDate',
    'DemandDate',
    'OrderDate',
    'DaysDelay'
  ]
  jsonToExcel(
    header,
    customerOpenItems.value,
    props.customerCode + '-OpenItems'
  )
}

onMounted(() => {
  console.debug('onMounted CustomerOpenItems')
  if (props.customerCode) {
    doUpdate()
  }
})

// Don't use watchEffect, it run before Mounted.
watch(
  () => [props.customerCode, props.dateFrom, props.dateTo],
  (...newAndold) => {
    // newAndold[1]:old
    // newAndold[0]:new
    console.debug('watch:' + newAndold[1] + ' ---> ' + newAndold[0])
    // customerCode must not be null
    if (newAndold[0][0]) {
      doUpdate()
    }
  }
)
</script>
