<!--
 * @Author         : Robert Huang<56649783@qq.com>
 * @Date           : 2022-03-25 11:01:23
 * @LastEditors    : Robert Huang<56649783@qq.com>
 * @LastEditTime   : 2022-05-29 01:01:20
 * @FilePath       : \web2\src\components\echarts\EchartSalesHistory.vue
 * @CopyRight      : Dedienne Aerospace China ZhuHai
-->
<template>
  <q-item>
    <div id="EchartSalesHistory" style="height: 100%; width: 100%" />
    <q-inner-loading :showing="showLoading">
      <q-spinner-ios size="50px" color="primary" />
    </q-inner-loading>
  </q-item>
</template>

<script setup>
import { axiosGet } from '@/assets/axiosActions'
import {
  AttachedPieSerial,
  defaultDataZoom,
  defaultLegend,
  defaultLineSerial,
  defaultToolbox,
  defaultTooltip,
  defaultXAxisTime,
  defaultYAxisUSD,
  echarts
} from '@/assets/echartsCfg.js'
import _forEach from 'lodash/forEach'
import _get from 'lodash/get'
import _groupBy from 'lodash/groupBy'
import _map from 'lodash/map'
import _sumBy from 'lodash/sumBy'
import _uniq from 'lodash/uniq'
import {
  onActivated,
  onBeforeUnmount,
  onDeactivated,
  onMounted,
  ref,
  watch
} from 'vue'
import { useI18n } from 'vue-i18n'

const props = defineProps({
  pnRoot: String
})

// common vars
const { t } = useI18n()
const showLoading = ref(false)

// echart vars
let eChart = null
let data = []
let lengend = []
let dataByLengend = []
let dataCountedByLengend = []
let dataset = []
let series = []
const dimensions = [
  'SalesSite',
  'OrderNO',
  'OrderDate',
  'PN',
  'Qty',
  'Currency',
  'NetPrice',
  'USD',
  'Rate',
  'TradeTerm',
  'CustomerCode',
  'CustomerName'
]
let dataZoomStartValue = '1900-01-01'

// actions
const doUpdate = () => {
  if (!props.pnRoot) return

  showLoading.value = true

  axiosGet('/Data/SalesHistory?PnRoot=' + props.pnRoot)
    .then((response) => {
      data = response
      prepareData()
      setEchart()
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

  lengend = _uniq(_map(data, 'SalesSite'))
  dataByLengend = _groupBy(data, 'SalesSite')
  dataCountedByLengend = []
  dataset = []
  series = []

  _forEach(dataByLengend, (value) => {
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

  lengend.forEach((value, index) => {
    dataset[index] = { source: dataByLengend[value] }
    series[index] = defaultLineSerial(
      index,
      value,
      '{@NetPrice} {@Currency}',
      dimensions,
      'OrderDate',
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
        text: t('Sales History'),
        subtext: t(
          'Currency Rate Data From State Administration of Foreign Exchange'
        ),
        left: 'center'
      },
      legend: defaultLegend,
      grid: [{ left: '5%', right: '25%' }],
      toolbox: defaultToolbox(dimensions, data, t('Sales History')),
      tooltip: defaultTooltip,
      dataZoom: defaultDataZoom('x', dataZoomStartValue),
      xAxis: defaultXAxisTime,
      yAxis: defaultYAxisUSD,
      dataset: dataset,
      series: series
    },
    true
  )
}

const resize = () => {
  eChart.resize()
}

// events
onMounted(() => {
  eChart = echarts.init(document.getElementById('EchartSalesHistory'))
  doUpdate()
})

onBeforeUnmount(() => {
  eChart.dispose()
})

onActivated(() => {
  // when use keep alive, must use activated/deactivated
  window.addEventListener('resize', resize)
  resize()
})

onDeactivated(() => {
  // when use keep alive, must use activated/deactivated
  window.removeEventListener('resize', resize)
})

watch(
  // Don't use watchEffect, it run before Mounted.
  () => [props.pnRoot],
  (...newAndold) => {
    // newAndold[1]:old
    // newAndold[0]:new
    console.debug('watch:' + newAndold[1] + ' ---> ' + newAndold[0])

    doUpdate()
  }
)
</script>
