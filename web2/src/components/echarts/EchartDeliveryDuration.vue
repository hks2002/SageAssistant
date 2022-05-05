<template>
  <q-item>
    <div id="EchartDeliveryDuration" style="height: 100%; width: 100%" />
    <q-inner-loading :showing="showLoading">
      <q-spinner-ios size="50px" color="primary" />
    </q-inner-loading>
  </q-item>
</template>

<script setup>
import { ref, onMounted, watch } from 'vue'
import { notifyError } from 'assets/common'
import { useI18n } from 'vue-i18n'
import { axios } from 'boot/axios'

import _groupBy from 'lodash/groupBy'
import _forEach from 'lodash/forEach'
import _sumBy from 'lodash/sumBy'
import _uniq from 'lodash/uniq'
import _map from 'lodash/map'
import _get from 'lodash/get'

import {
  echarts,
  defaultTooltip,
  defaultToolbox,
  defaultLegend,
  defaultLineSerial,
  defaultXAxisTime
} from 'assets/echartsCfg.js'

const props = defineProps({
  pnRoot: String
})

const { t } = useI18n({ useScope: 'global' })

let eChart = null
let data = []
let lengend = []
let dataByLengend = []
let dataset = []
let series = []
const dimensions = ['SalesSite', 'PN', 'OrderDate', 'ShipDate', 'Duration']
const showLoading = ref(false)

const doUpdate = (pnRoot) => {
  showLoading.value = true

  axios
    .get('/Data/DeliveryDuration?PnRoot=' + pnRoot)
    .then((response) => {
      data = response.data
      prepareData()
      setEchart()
    })
    .catch((e) => {
      console.error(e)
      notifyError(t('Loading Delivery Duration Failed!'))
    })
    .finally(() => {
      showLoading.value = false
    })
}

const prepareData = () => {
  lengend = _uniq(_map(data, 'SalesSite'))
  dataByLengend = _groupBy(data, 'SalesSite')
  dataset = []
  series = []

  _forEach(lengend, (value, index) => {
    dataset[index] = { source: dataByLengend[value] }
    series[index] = defaultLineSerial(
      index,
      value,
      '{@Duration}',
      dimensions,
      'ShipDate',
      'Duration'
    )
  })
}

const setEchart = () => {
  // data is ready,set echart option
  eChart.setOption(
    {
      title: {
        text: t('Delivery Duration'),
        left: 'center'
      },
      tooltip: defaultTooltip,
      legend: defaultLegend,
      toolbox: defaultToolbox(dimensions, data, t('Delivery Duration')),
      xAxis: defaultXAxisTime,
      yAxis: {
        type: 'value',
        min: 0,
        max: function (value) {
          if (isNaN(value.max)) {
            return 90
          } else {
            return null
          }
        },
        minInterval: 1,
        axisLabel: {
          formatter: '{value}'
        }
      },
      dataset: dataset,
      series: series
    },
    true
  )
}

onMounted(() => {
  console.debug('onMounted EchartDeliveryDuration')
  eChart = echarts.init(document.getElementById('EchartDeliveryDuration'))
  if (props.pnRoot) {
    doUpdate(props.pnRoot)
  }
})

// Don't use watchEffect, it run before Mounted.
watch(
  () => props.pnRoot,
  (newVal, oldVal) => {
    console.debug('watch:' + oldVal + ' ---> ' + newVal)
    if (newVal) {
      doUpdate(newVal)
    }
  }
)
</script>
