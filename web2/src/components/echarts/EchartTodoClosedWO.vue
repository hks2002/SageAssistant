<!--
 * @Author         : Robert Huang<56649783@qq.com>
 * @Date           : 2022-03-25 11:01:23
 * @LastEditors    : Robert Huang<56649783@qq.com>
 * @LastEditTime   : 2022-05-28 22:56:13
 * @FilePath       : \web2\src\components\echarts\EchartTodoClosedWO.vue
 * @CopyRight      : Dedienne Aerospace China ZhuHai
-->
<template>
  <q-item>
    <div id="EchartTodoClosedWO" style="height: 100%; width: 100%" />
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
  defaultScatterSerial,
  defaultToolbox,
  defaultTooltip,
  defaultXAxisTime,
  echarts
} from '@/assets/echartsCfg.js'
import _groupBy from 'lodash/groupBy'
import _map from 'lodash/map'
import _sortBy from 'lodash/sortBy'
import _uniq from 'lodash/uniq'
import {
  computed,
  onActivated,
  onBeforeUnmount,
  onDeactivated,
  onMounted,
  ref,
  watch
} from 'vue'
import { useI18n } from 'vue-i18n'

const props = defineProps({
  site: String
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
  'ProjectNO',
  'OrderNO',
  'WorkOrderNO',
  'WOStatus',
  'ProductionStatus',
  'OrderType',
  'CustomerCode',
  'CustomerName',
  'PN',
  'Qty',
  'OrderDate'
]

// computed vars
const title = computed(() => {
  return (
    t('Sales order line is closed but its WorkOrder line is still open ') +
    ' [' +
    props.site +
    ']'
  )
})

// actions
const doUpdate = () => {
  if (!props.site) return

  showLoading.value = true

  axiosGet('/Data/TobeClosedWO?Site=' + props.site)
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
  data = _sortBy(data, ['WorkOrderNO'])
  lengend = _uniq(_map(data, 'ProductionStatus'))
  dataByLengend = _groupBy(data, 'ProductionStatus')
  dataset = []
  series = []

  lengend.forEach((value, index) => {
    dataset[index] = { source: dataByLengend[value] }
    series[index] = defaultScatterSerial(
      index,
      value,
      '{@WorkOrderNO}',
      dimensions,
      'OrderDate',
      'WorkOrderNO'
    )
  })
}

const setEchart = () => {
  // data is ready,set echart option
  eChart.setOption(
    {
      title: {
        text: title.value,
        left: 'center'
      },
      legend: defaultLegend,
      toolbox: defaultToolbox(dimensions, data, title.value),
      tooltip: defaultTooltip,
      xAxis: defaultXAxisTime,
      grid: [{ left: 40, right: 40 }],
      yAxis: [
        {
          type: 'category',
          show: false
        }
      ],
      dataZoom: defaultDataZoom('xy'),
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
  eChart = echarts.init(document.getElementById('EchartTodoClosedWO'))
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
  () => [props.site],
  (...newAndold) => {
    // newAndold[1]:old
    // newAndold[0]:new
    console.debug('watch:' + newAndold[1] + ' ---> ' + newAndold[0])

    doUpdate()
  }
)
</script>
