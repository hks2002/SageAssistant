<template>
  <q-item>
    <div id="EchartCustomerOpenAmount" style="height: 100%; width: 100%" />
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
  defaultBarStackedSerial
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
const dimensions = ['Site', 'CustomerCode', 'Amount', 'Currency', 'USD', 'Rate']
const showLoading = ref(false)

const doUpdate = () => {
  showLoading.value = true

  axios
    .get('/Data/CustomerOpenAmount?CustomerCode=' + props.customerCode)
    .then((response) => {
      data = response.data
      prepareData()
      setEchart()
    })
    .catch((e) => {
      console.error(e)
      notifyError(t('Loading Customer Open Amount Failed!'))
    })
    .finally(() => {
      showLoading.value = false
    })
}

const prepareData = () => {
  lengend = _uniq(_map(data, 'Currency'))
  dataByLengend = _groupBy(data, 'Currency')
  sites = _uniq(_map(data, 'Site'))
  dataset = []
  series = []

  _forEach(lengend, (value, index) => {
    dataset[index] = { source: dataByLengend[value] }
    series[index] = defaultBarStackedSerial(
      index,
      value,
      '{@USD}',
      dimensions,
      'Site',
      'USD'
    )
  })
}

const setEchart = () => {
  // data is ready,set echart option
  eChart.setOption(
    {
      title: {
        text: t('Open Amount(All)'),
        subtext: '',
        left: 'center'
      },
      legend: defaultLegend,
      toolbox: defaultToolbox(dimensions, data, t('Open Amount(All)')),
      tooltip: defaultTooltip,
      xAxis: {
        type: 'category',
        data: sites
      },
      yAxis: {
        type: 'value',
        name: 'USD',
        nameLocation: 'start',
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
  console.debug('onMounted EchartCustomerOpenAmount')
  eChart = echarts.init(document.getElementById('EchartCustomerOpenAmount'))
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
