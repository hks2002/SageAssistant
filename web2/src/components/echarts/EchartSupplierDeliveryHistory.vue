<template>
  <q-item>
    <div id="EchartSupplierDeliveryHistory" style="height: 100%; width: 100%" />
    <q-inner-loading :showing="showLoading">
      <q-spinner-ios size="50px" color="primary" />
    </q-inner-loading>
  </q-item>
</template>

<script setup>
import { onMounted, ref, watch } from 'vue'
import { date } from 'quasar'
import { notifyError } from 'assets/common'
import { useI18n } from 'vue-i18n'
import { axios } from 'boot/axios'

import {
  echarts,
  defaultTooltip,
  defaultToolbox,
  defaultLegend,
  defaultDataZoom,
  defaultXAxisTime,
  mergerOption,
  jsonToMultLine
} from 'assets/echartsCfg.js'

import _groupBy from 'lodash/groupBy'
import _forEach from 'lodash/forEach'
import _uniq from 'lodash/uniq'
import _map from 'lodash/map'

const props = defineProps({
  supplierCode: String,
  dateFrom: String,
  dateTo: String
})

const { t } = useI18n({ useScope: 'global' })

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
const showLoading = ref(false)

const doUpdate = () => {
  if (!date.isValid(props.dateFrom) || !date.isValid(props.dateTo)) {
    return
  }
  showLoading.value = true

  axios
    .get(
      '/Data/SupplierDeliveryHistory?SupplierCode=' +
        props.supplierCode +
        '&DateFrom=' +
        props.dateFrom +
        '&DateTo=' +
        props.dateTo
    )
    .then((response) => {
      data = response.data
      prepareData()
      setEchart()
    })
    .catch((e) => {
      console.error(e)
      notifyError(t('Loading Supplier Delivery History Failed!'))
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
          formatter: (params, ticket, callback) => {
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
    dataZoom: defaultDataZoom(),
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

onMounted(() => {
  console.debug('onMounted EchartSupplierDeliveryHistory')
  eChart = echarts.init(
    document.getElementById('EchartSupplierDeliveryHistory')
  )
  if (props.supplierCode) {
    doUpdate()
  }
})

// Don't use watchEffect, it run before Mounted.
watch(
  () => [props.supplierCode, props.dateFrom, props.dateTo],
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
