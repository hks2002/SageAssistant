<template>
  <q-item>
    <div id="EchartCostHistory" style="height: 100%; width: 100%" />
    <q-inner-loading :showing="showLoading">
      <q-spinner-ios size="50px" color="primary" />
    </q-inner-loading>
  </q-item>
</template>

<script setup>
import { notifyError } from 'assets/common'
import {
  defaultDataZoom,
  defaultLegend,
  defaultLineSerial,
  defaultToolbox,
  defaultTooltip,
  defaultXAxisTime,
  defaultYAxisUSD,
  echarts
} from 'assets/echartsCfg.js'
import { axios } from 'boot/axios'
import _forEach from 'lodash/forEach'
import _get from 'lodash/get'
import _groupBy from 'lodash/groupBy'
import _map from 'lodash/map'
import _sumBy from 'lodash/sumBy'
import _uniq from 'lodash/uniq'
import { onMounted, ref, watch } from 'vue'
import { useI18n } from 'vue-i18n'

const props = defineProps({
  pnRoot: String
})

const { t } = useI18n({ useScope: 'global' })

let eChart = null
let data = []
let lengend = []
let dataByLengend = []
let dataBySiteProject = []
let dataSumBySiteProject = []
let dataset = []
let series = []
const dimensions = [
  'PurchaseSite',
  'ProjectNO',
  'OrderPN',
  'OrderDate',
  'PurchaseNO',
  'Line',
  'VendorCode',
  'VendorName',
  'PurchasePN',
  'Description',
  'Qty',
  'Currency',
  'NetPrice',
  'USD',
  'Rate'
]
const miniDemensions = [
  'PurchaseSite',
  'ProjectNO',
  'OrderPN',
  'OrderDate',
  'USD'
]
let dataZoomStartValue = '1900-01-01'
const showLoading = ref(false)

const doUpdate = () => {
  showLoading.value = true

  axios
    .get('/Data/CostHistory?PnRoot=' + props.pnRoot)
    .then((response) => {
      data = response.data
      prepareData()
      setEchart()
    })
    .catch((e) => {
      console.error(e)
      notifyError(t('Loading Cost History Failed!'))
    })
    .finally(() => {
      showLoading.value = false
    })
}

const prepareData = () => {
  const len = data.length
  if (len >= 20) {
    dataZoomStartValue = _get(data[len - 20], 'OrderDate')
  } else if (len > 0) {
    dataZoomStartValue = _get(data[0], 'OrderDate')
  }
  dataSumBySiteProject = []
  dataset = []
  series = []

  dataBySiteProject = _groupBy(data, function (n) {
    return _get(n, 'PurchaseSite') + _get(n, 'ProjectNO')
  })
  _forEach(dataBySiteProject, (value, index, array) => {
    const o = {}
    Object.defineProperty(o, 'PurchaseSite', {
      enumerable: true,
      value: _get(value[0], 'PurchaseSite')
    })
    Object.defineProperty(o, 'ProjectNO', {
      enumerable: true,
      value: _get(value[0], 'ProjectNO')
    })
    Object.defineProperty(o, 'OrderPN', {
      enumerable: true,
      value: _get(value[0], 'OrderPN')
    })
    Object.defineProperty(o, 'OrderDate', {
      enumerable: true,
      value: _get(value[0], 'OrderDate')
    })
    Object.defineProperty(o, 'USD', {
      enumerable: true,
      value: _sumBy(value, 'USD')
    })
    dataSumBySiteProject.push(o)
  })
  lengend = _uniq(_map(data, 'PurchaseSite'))
  dataByLengend = _groupBy(dataSumBySiteProject, 'PurchaseSite')

  _forEach(lengend, (value, index) => {
    dataset[index] = { source: dataByLengend[value] }
    series[index] = defaultLineSerial(
      index,
      value,
      '{@USD} USD',
      miniDemensions,
      'OrderDate',
      'USD'
    )
  })
}

const setEchart = () => {
  // data is ready,set echart option
  eChart.setOption(
    {
      title: {
        text: 'Sales Order Cost History',
        subtext:
          'Currency Rate Data From State Administration of Foreign Exchange',
        left: 'center'
      },
      legend: defaultLegend,
      grid: [{ left: '5%', right: '25%' }],
      toolbox: defaultToolbox(dimensions, data, 'Cost History'),
      tooltip: defaultTooltip,
      dataZoom: defaultDataZoom(dataZoomStartValue),
      xAxis: defaultXAxisTime,
      yAxis: defaultYAxisUSD,
      dataset: dataset,
      series: series
    },
    true
  )
}

onMounted(() => {
  console.debug('onMounted EchartCostHistory')
  eChart = echarts.init(document.getElementById('EchartCostHistory'))
  if (props.pnRoot) {
    doUpdate(props.pnRoot)
  }
})

// Don't use watchEffect, it run before Mounted.
watch(
  () => [props.pnRoot],
  (...newAndold) => {
    // newAndold[1]:old
    // newAndold[0]:new
    console.debug('watch:' + newAndold[1] + ' ---> ' + newAndold[0])
    if (newAndold[0][0]) {
      doUpdate()
    }
  }
)
</script>
