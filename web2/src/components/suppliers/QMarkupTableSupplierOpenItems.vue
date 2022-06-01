<template>
  <q-markup-table
    dense
    v-if="supplierOpenItems.length > 0"
    style="height: 250px"
  >
    <thead style="position: sticky; top: 0px; z-index: 1">
      <tr>
        <td colspan="11" class="bg-teal text-h6 text-white shadow-2">
          {{ $t('OpenItems') }}({{ $t('All') }}-{{ supplierOpenItems.length }})
          <q-btn dense flat icon="fas fa-download" @click="download()" />
        </td>
      </tr>
      <tr class="bg-primary text-white">
        <th class="text-center text-caption">#</th>
        <th class="text-left">Site</th>
        <th class="text-left">SupplierCode</th>
        <th class="text-left">PurchaseNO</th>
        <th class="text-left">ProjectNO</th>
        <th class="text-left">PN</th>
        <th class="text-left">Description</th>
        <th class="text-center">AckDate</th>
        <th class="text-center">ExpectDate</th>
        <th class="text-center">OrderDate</th>
        <th class="text-center">DaysDelay</th>
      </tr>
    </thead>
    <tbody>
      <tr v-for="(item, index) in supplierOpenItems" :key="index">
        <td class="text-center">{{ index }}</td>
        <td>{{ item['Site'] }}</td>
        <td>{{ item['SupplierCode'] }}</td>
        <td>{{ item['PurchaseNO'] }}</td>
        <td>{{ item['ProjectNO'] }}</td>
        <td>{{ item['PN'] }}</td>
        <td>{{ item['Description'] }}</td>
        <td class="text-center">{{ item['AckDate'] }}</td>
        <td class="text-center">{{ item['ExpectDate'] }}</td>
        <td class="text-center">{{ item['OrderDate'] }}</td>
        <td class="text-center">{{ item['DaysDelay'] }}</td>
      </tr>
    </tbody>
  </q-markup-table>
</template>

<script setup>
import { axiosGet } from '@/assets/axiosActions'
import { jsonToExcel } from 'assets/dataUtils'
import { onMounted, ref, watch } from 'vue'
import { useI18n } from 'vue-i18n'

const props = defineProps({
  supplierCode: String,
  dateFrom: String,
  dateTo: String
})

// common vars
const { t } = useI18n()
const showLoading = ref(false)

// component vars
const supplierOpenItems = ref([])

// actions
const doUpdate = () => {
  if (!props.supplierCode) return

  showLoading.value = true

  axiosGet(
    '/Data/SupplierOpenItems?SupplierCode=' +
      props.supplierCode +
      '&DateFrom=' +
      props.dateFrom +
      '&DateTo=' +
      props.dateTo
  )
    .then((response) => {
      supplierOpenItems.value = response
    })
    .finally(() => {
      showLoading.value = false
    })
}

const download = () => {
  const header = [
    'Site',
    'SupplierCode',
    'PurchaseNO',
    'ProjectNO',
    'PN',
    'Description',
    'AckDate',
    'ExpectDate',
    'OrderDate',
    'DaysDelay'
  ]
  jsonToExcel(
    header,
    supplierOpenItems.value,
    props.supplierCode + '-' + t('OpenItems')
  )
}

// events
onMounted(() => {
  doUpdate()
})

// Don't use watchEffect, it run before Mounted.
watch(
  () => [props.supplierCode, props.dateFrom, props.dateTo],
  (...newAndold) => {
    // newAndold[1]:old
    // newAndold[0]:new
    console.debug('watch:' + newAndold[1] + ' ---> ' + newAndold[0])
    // supplierCode must not be null

    doUpdate()
  }
)
</script>
