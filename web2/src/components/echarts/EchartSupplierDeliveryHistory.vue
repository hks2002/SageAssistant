<template>
  <q-item>
    <div id="EchartSupplierDeliveryHistory" style="height: 100%; width: 100%" />
    <q-inner-loading :showing="showLoading">
      <q-spinner-ios size="50px" color="primary" />
    </q-inner-loading>
  </q-item>
</template>

<script setup>
import { axiosGet } from '@/assets/axiosActions'
import {
  defaultDataZoom,
  defaultLegend,
  defaultToolbox,
  defaultTooltip,
  defaultXAxisTime,
  echarts,
  jsonToMultLine,
  mergerOption
} from '@/assets/echartsCfg.js'
import _forEach from 'lodash/forEach'
import _groupBy from 'lodash/groupBy'
import _map from 'lodash/map'
import _uniq from 'lodash/uniq'
import { date } from 'quasar'
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
  supplierCode: String,
  dateFrom: String,
  dateTo: String
})
// common vars
const { t } = useI18n()
const showLoading = ref(false)

// echart vars
let eChart = null
let data = []
let lengend = []
let dataByLengend = []
let dataset = []
let series = []
const dimensions = [
  'Site',
  'SupplierCode',
  'PurchaseNO',
  'ProjectNO',
  'PN',
  'Description',
  'AckDate',
  'ExpectDate',
  'OrderDate',
  'ReceiptNO',
  'ReceiptDate',
  'DaysNeed'
]

// actions
const doUpdate = () => {
  if (
    !props.supplierCode ||
    !date.isValid(props.dateFrom) ||
    !date.isValid(props.dateTo)
  ) {
    return
  }

  showLoading.value = true

  axiosGet(
    '/Data/SupplierDeliveryHistory?SupplierCode=' +
      props.supplierCode +
      '&DateFrom=' +
      props.dateFrom +
      '&DateTo=' +
      props.dateTo
  )
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
  lengend = _uniq(_map(data, 'Site'))
  dataByLengend = _groupBy(data, 'Site')
  dataset = []
  series = []

  _forEach(
    lengend,
    (value, index) => {
      // dataset
      dataset[index] = { source: dataByLengend[value] }
      // series
      series[index] = {
        type: 'line',
        name: value,
        datasetIndex: index,
        lineStyle: {
          width: 1
        },
        symbolSize: 1,
        animation: false,
        areaStyle: {},
        tooltip: {
          trigger: 'item',
          formatter: (params) => {
            return jsonToMultLine(dimensions, params.data)
          }
        },
        dimensions: dimensions,
        encode: {
          x: 'ReceiptDate',
          y: 'DaysNeed'
        }
      }
    },
    true
  )
}

const setEchart = () => {
  // data is ready,set echart option
  eChart.setOption({
    title: {
      text:
        t('Delivery History') +
        '(' +
        props.dateFrom +
        '-->' +
        props.dateTo +
        ')',
      subtext: '',
      left: 'center'
    },
    legend: defaultLegend,
    toolbox: defaultToolbox(
      dimensions,
      data,
      t('Delivery History') + '(' + props.dateFrom + '-->' + props.dateTo + ')'
    ),
    tooltip: defaultTooltip,
    dataZoom: defaultDataZoom('x'),
    xAxis: mergerOption(defaultXAxisTime, { name: 'Receipt' }),
    yAxis: {
      min: 0,
      max: function (value) {
        if (isNaN(value.max)) {
          return 90
        } else {
          return null
        }
      },
      minInterval: 1
    },
    dataset: dataset,
    series: series
  })
}

const resize = () => {
  eChart.resize()
}

// events
onMounted(() => {
  eChart = echarts.init(
    document.getElementById('EchartSupplierDeliveryHistory')
  )
  // when not use keep alive, use mounted/unmounted
  window.addEventListener('resize', resize)
  doUpdate()
})

onBeforeUnmount(() => {
  // when not use keep alive, use mounted/unmounted
  window.removeEventListener('resize', resize)
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
  () => [props.supplierCode, props.dateFrom, props.dateTo],
  (...newAndold) => {
    // newAndold[1]:old
    // newAndold[0]:new
    console.debug('watch:' + newAndold[1] + ' ---> ' + newAndold[0])

    doUpdate()
  }
)
</script>
