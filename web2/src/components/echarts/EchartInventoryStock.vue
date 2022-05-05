<template>
  <q-item>
    <div id="EchartInventoryStock" style="height: 100%; width: 100%" />
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
  defaultBarStackedSerial
} from 'assets/echartsCfg.js'

const props = defineProps({
  pnRoot: String
})

const { t } = useI18n({ useScope: 'global' })

let eChart = null
let data = []
let lengend = []
let dataByLengend = []
let bars = []
let dataset = []
let series = []
const dimensions = ['PN', 'StockSite', 'Qty']
const showLoading = ref(false)

const doUpdate = (pnRoot) => {
  showLoading.value = true

  axios
    .get('/Data/InventoryStock?PnRoot=' + pnRoot)
    .then((response) => {
      data = response.data
      prepareData()
      setEchart()
    })
    .catch((e) => {
      console.error(e)
      notifyError(t('Loading Inventory Stock Summary Failed!'))
    })
    .finally(() => {
      showLoading.value = false
    })
}

const prepareData = () => {
  bars = _uniq(_map(data, 'StockSite'))
  lengend = _uniq(_map(data, 'PN'))
  dataByLengend = _groupBy(data, 'PN')
  dataset = []
  series = []

  _forEach(lengend, (value, index) => {
    dataset[index] = { source: dataByLengend[value] }
    series[index] = defaultBarStackedSerial(
      index,
      value,
      '{@Qty}',
      dimensions,
      'StockSite',
      'Qty'
    )
  })
}

const setEchart = () => {
  // data is ready,set echart option
  eChart.setOption(
    {
      title: {
        text: t('Stock Info'),
        left: 'center'
      },
      tooltip: defaultTooltip,
      legend: defaultLegend,
      toolbox: defaultToolbox(dimensions, data, t('Stock Info')),
      xAxis: {
        type: 'category',
        data: bars
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
  console.debug('onMounted EchartInventoryStock')
  eChart = echarts.init(document.getElementById('EchartInventoryStock'))
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
