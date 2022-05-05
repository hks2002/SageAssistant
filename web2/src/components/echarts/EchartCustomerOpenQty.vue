<template>
  <q-item>
    <div id="EchartCustomerOpenQty" style="height: 100%; width: 100%" />
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
  defaultTooltip,
  defaultToolbox,
  defaultLegend,
  defaultBarSerial
} from 'assets/echartsCfg.js'

import _groupBy from 'lodash/groupBy'
import _forEach from 'lodash/forEach'
import _uniq from 'lodash/uniq'
import _map from 'lodash/map'

const props = defineProps({
  customerCode: String,
  dateFrom: String,
  dateTo: String
})

const { t } = useI18n({ useScope: 'global' })

let eChart = null
let data = []
let lengend = []
let dataByLengend = []
let sites = []
let dataset = []
let series = []
const dimensions = ['Site', 'CustomerCode', 'CountType', 'Qty']
const showLoading = ref(false)

const doUpdate = () => {
  showLoading.value = true

  axios
    .get('/Data/CustomerOpenQty?CustomerCode=' + props.customerCode)
    .then((response) => {
      data = response.data
      prepareData()
      setEchart()
    })
    .catch((e) => {
      console.error(e)
      notifyError(t('Loading Customer Open Qty Failed!'))
    })
    .finally(() => {
      showLoading.value = false
    })
}

const prepareData = () => {
  lengend = _uniq(_map(data, 'CountType'))
  dataByLengend = _groupBy(data, 'CountType')
  sites = _uniq(_map(data, 'Site'))
  dataset = []
  series = []

  _forEach(lengend, (value, index) => {
    dataset[index] = { source: dataByLengend[value] }
    series[index] = defaultBarSerial(
      index,
      value,
      '{@Qty}',
      dimensions,
      'Site',
      'Qty'
    )
  })
}

const setEchart = () => {
  // data is ready,set echart option
  eChart.setOption(
    {
      title: {
        text: t('Open Qty(All)'),
        subtext: '',
        left: 'center'
      },
      legend: defaultLegend,
      toolbox: defaultToolbox(dimensions, data, t('Open Qty(All)')),
      tooltip: defaultTooltip,
      xAxis: {
        type: 'category',
        data: sites
      },
      yAxis: {
        type: 'value',
        min: 0,
        max: function (value) {
          if (isNaN(value.max)) {
            return 10
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
  console.debug('onMounted EchartCustomerOpenQty')
  eChart = echarts.init(document.getElementById('EchartCustomerOpenQty'))
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
    if (newAndold[0][0]) {
      doUpdate()
    }
  }
)
</script>
