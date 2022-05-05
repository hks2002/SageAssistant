<template>
  <q-item>
    <div id="EchartQuoteHistory" style="height: 100%; width: 100%" />
    <q-inner-loading :showing="showLoading">
      <q-spinner-ios size="50px" color="primary" />
    </q-inner-loading>
  </q-item>
</template>

<script setup>
import { onMounted, ref, watch } from 'vue'
import { notifyError } from 'assets/common'
import { useI18n } from 'vue-i18n'
import { axios } from 'boot/axios'

import {
  echarts,
  defaultXAxisTime,
  defaultYAxisUSD,
  defaultTooltip,
  defaultDataZoom,
  defaultToolbox,
  defaultLegend,
  defaultLineSerial,
  AttachedPieSerial
} from 'assets/echartsCfg.js'

import _groupBy from 'lodash/groupBy'
import _forEach from 'lodash/forEach'
import _sumBy from 'lodash/sumBy'
import _uniq from 'lodash/uniq'
import _map from 'lodash/map'
import _get from 'lodash/get'

const props = defineProps({
  pnRoot: String
})

const { t } = useI18n({ useScope: 'global' })

let eChart = null
let data = []
let lengend = []
let dataByLengend = []
let dataCountedByLengend = []
let dataset = []
let series = []
const dimensions = [
  'SalesSite',
  'QuoteNO',
  'QuoteDate',
  'PN',
  'Qty',
  'Currency',
  'NetPrice',
  'USD',
  'Rate',
  'TradeTerm',
  'CustomerCode',
  'CustomerName',
  'OrderFlag',
  'OrderNO'
]
let dataZoomStartValue = '1900-01-01'
const showLoading = ref(false)

const doUpdate = () => {
  showLoading.value = true

  axios
    .get('/Data/QuoteHistory?PnRoot=' + props.pnRoot)
    .then((response) => {
      data = response.data
      prepareData()
      setEchart()
    })
    .catch((e) => {
      console.error(e)
      notifyError(t('Loading Quotes History Failed!'))
    })
    .finally(() => {
      showLoading.value = false
    })
}

const prepareData = () => {
  const len = data.length
  if (len >= 20) {
    dataZoomStartValue = _get(data[len - 20], 'QuoteDate')
  } else if (len > 0) {
    dataZoomStartValue = _get(data[0], 'QuoteDate')
  }

  lengend = _uniq(_map(data, 'SalesSite'))
  dataByLengend = _groupBy(data, 'SalesSite')
  dataCountedByLengend = []
  dataset = []
  series = []

  _forEach(dataByLengend, (value, index, array) => {
    const o = {}
    Object.defineProperty(o, 'SalesSite', {
      enumerable: true,
      value: _get(value[0], 'SalesSite')
    })
    Object.defineProperty(o, 'Qty', {
      enumerable: true,
      value: _sumBy(value, 'Qty')
    })
    dataCountedByLengend.push(o)
  })

  _forEach(lengend, (value, index) => {
    dataset[index] = { source: dataByLengend[value] }
    series[index] = defaultLineSerial(
      index,
      value,
      '{@NetPrice} {@Currency}',
      dimensions,
      'QuoteDate',
      'USD'
    )
  })

  // add pie
  dataset.push({ source: dataCountedByLengend })
  const seriesBySite = AttachedPieSerial(
    dataset.length - 1,
    '{@SalesSite} \nQty:{@Qty}\n{d}%',
    ['SalesSite', 'Qty'],
    'SalesSite',
    'Qty'
  )
  series.push(seriesBySite)
}

const setEchart = () => {
  // data is ready,set echart option
  eChart.setOption(
    {
      title: {
        text: t('Quotes History'),
        subtext: t(
          'Currency Rate Data From State Administration of Foreign Exchange'
        ),
        left: 'center'
      },
      legend: defaultLegend,
      grid: [{ left: '5%', right: '25%' }],
      toolbox: defaultToolbox(dimensions, data, t('Quotes History')),
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
  console.debug('onMounted EchartQuoteHistory')
  eChart = echarts.init(document.getElementById('EchartQuoteHistory'))
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
