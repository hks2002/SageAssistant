<!--
 * @Author         : Robert Huang<56649783@qq.com>
 * @Date           : 2022-04-01 16:30:58
 * @LastEditors    : Robert Huang<56649783@qq.com>
 * @LastEditTime   : 2022-05-29 03:12:44
 * @FilePath       : \web2\src\components\stock\QMarkupTableStockHistory.vue
 * @CopyRight      : Dedienne Aerospace China ZhuHai
-->
<template>
  <q-markup-table dense bordered class="col-grow">
    <thead style="position: sticky; top: 0px; z-index: 1">
      <tr>
        <td colspan="17" class="bg-teal text-white shadow-2">
          {{ site }} {{ $t('Stock History') }}
          <span v-if="PnOrName">of {{ PnOrName }} </span> {{ $t('from') }}
          {{ dateFrom }} {{ $t('to') }}
          {{ dateTo }}
          <q-btn dense flat icon="fas fa-download" @click="download()" />
        </td>
      </tr>
      <tr class="bg-primary text-white">
        <th class="text-center text-caption">#</th>
        <th class="text-left">Location</th>
        <th class="text-left">Seq</th>
        <th class="text-left">PN</th>
        <th class="text-left">Description</th>
        <th class="text-center">Qty</th>
        <th class="text-center">Unit</th>
        <th class="text-right">Cost</th>
        <th class="text-left">ProjectNO</th>
        <th class="text-left">SourceNO</th>
        <th class="text-left">SourceLine</th>
        <th class="text-left">EntryNO</th>
        <th class="text-left">EntryLine</th>
        <th class="text-center">CreateUser</th>
        <th class="text-center">CreateDate</th>
      </tr>
    </thead>
    <tbody>
      <tr v-for="(item, index) in stockHistory" :key="index">
        <td class="text-center">{{ index }}</td>
        <td>{{ item['Location'] }}</td>
        <td>{{ item['Seq'] }}</td>
        <td>{{ item['PN'] }}</td>
        <td>{{ item['Description'] }}</td>
        <td class="text-center">{{ item['Qty'] }}</td>
        <td class="text-center">{{ item['Unit'] }}</td>
        <td class="text-right">{{ item['Cost'] }}</td>
        <td>{{ item['ProjectNO'] }}</td>
        <td>{{ item['SourceNO'] }}</td>
        <td>{{ item['SourceLine'] }}</td>
        <td>{{ item['EntryNO'] }}</td>
        <td>{{ item['EntryLine'] }}</td>
        <td class="text-center">{{ item['CreateUser'] }}</td>
        <td class="text-center">{{ item['CreateDate'] }}</td>
      </tr>
    </tbody>
    <q-inner-loading :showing="showLoading">
      <q-spinner-ios size="50px" color="primary" />
    </q-inner-loading>
  </q-markup-table>
</template>

<script setup>
import { axiosGet } from '@/assets/axiosActions'
import { jsonToExcel } from '@/assets/dataUtils'
import { ebus } from '@/assets/ebus'
import { getCookies } from '@/assets/storage'
import { onBeforeUnmount, onMounted, ref, watch } from 'vue'
import { useI18n } from 'vue-i18n'

const props = defineProps({
  PnOrName: { type: String, require: false, default: '' },
  dateFrom: String,
  dateTo: String
})

// common vars
const { t } = useI18n()
const showLoading = ref(false)
const site = ref(getCookies('site'))

// component vars
const stockHistory = ref([])

// actions
const doUpdate = () => {
  showLoading.value = true

  axiosGet(
    '/Data/StockHistory?Site=' +
      site.value +
      '&PnOrName=' +
      props.PnOrName +
      '&DateFrom=' +
      props.dateFrom +
      '&DateTo=' +
      props.dateTo
  )
    .then((data) => {
      stockHistory.value = data
    })
    .finally(() => {
      showLoading.value = false
    })
}

const download = () => {
  const header = [
    'Location',
    'Seq',
    'PN',
    'Description',
    'Qty',
    'Unit',
    'Cost',
    'ProjectNO',
    'SourceNO',
    'SourceLine',
    'EntryNO',
    'EntryLine',
    'CreateUser',
    'CreateDate'
  ]
  const strPNData = data.value
  // PN with #
  _forEach(strPNData, (value) => {
    value.PN = '#' + value.PN
  })
  jsonToExcel(
    header,
    strPNData,
    site.value + t(' Stock History-') + props.dateFrom + '_' + props.dateTo
  )
}

// events
onMounted(() => {
  doUpdate()
})

ebus.on('changeSite', (newSite) => {
  site.value = newSite
  doUpdate()
})

onBeforeUnmount(() => {
  ebus.off('changeSite')
})

watch(
  // Don't use watchEffect, it run before Mounted.
  () => [props.PnOrName + props.dateFrom + props.dateTo],
  (...newAndold) => {
    // newAndold[1]:old
    // newAndold[0]:new
    console.debug('watch:' + newAndold[1] + ' ---> ' + newAndold[0])
    doUpdate()
  }
)
</script>
<style lang="scss" scoped></style>
