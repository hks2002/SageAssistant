<template>
  <q-item>
    <div id="EchartCustomerOpenAmount" style="height: 100%; width: 100%" />
    <q-inner-loading :showing="showLoading">
      <q-spinner-ios size="50px" color="primary" />
    </q-inner-loading>
  </q-item>
</template>

<script setup>
import { axiosGet } from '@/assets/axiosActions'
import {
  defaultBarStackedSerial,
  defaultLegend,
  defaultToolbox,
  defaultTooltip,
  echarts
} from '@/assets/echartsCfg.js'
import _forEach from 'lodash/forEach'
import _groupBy from 'lodash/groupBy'
import _map from 'lodash/map'
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
  customerCode: String,
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
let sites = []
let dataset = []
let series = []
const dimensions = ['Site', 'CustomerCode', 'Amount', 'Currency', 'USD', 'Rate']

// actions
const doUpdate = () => {
  if (!props.customerCode) return

  showLoading.value = true

  axiosGet('/Data/CustomerOpenAmount?CustomerCode=' + props.customerCode)
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

const resize = () => {
  eChart.resize()
}

// events
onMounted(() => {
  eChart = echarts.init(document.getElementById('EchartCustomerOpenAmount'))
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
  () => [props.customerCode, props.dateFrom, props.dateTo],
  (...newAndold) => {
    // newAndold[1]:old
    // newAndold[0]:new
    console.debug('watch:' + newAndold[1] + ' ---> ' + newAndold[0])

    doUpdate()
  }
)
</script>
