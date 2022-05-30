<template>
  <q-item>
    <div :id="eChartId" style="height: 100%; width: 100%" />
    <q-inner-loading :showing="showLoading">
      <q-spinner-ios size="50px" color="primary" />
    </q-inner-loading>
  </q-item>
</template>

<script setup>
import { axiosGet } from '@/assets/axiosActions'
import { defaultToolbox, defaultTooltip, echarts } from '@/assets/echartsCfg.js'
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

const props = defineProps({
  accountNO: { type: String, require: true, default: '' },
  year: { type: String, require: true, default: '' },
  site: { type: String, require: true, default: '' },
  cat: { type: String, require: true, default: '' }
})

// common vars
const showLoading = ref(false)

// echart vars
let eChart = null
let data = []
let lengend = []
let dataByLengend = []
let series = []
let catText = 'B'
const eChartId = 'EchartAccoutBalance' + props.cat

if (props.cat === 'C') {
  catText = 'Credit'
} else if (props.cat === 'D') {
  catText = 'Debit'
} else if (props.cat === 'M') {
  catText = 'Movement'
} else {
  catText = 'Balance'
}

const dimensions = ['AccountNO', 'Year', 'Currency']
for (let i = 0; i <= 12; i++) {
  dimensions.push(props.cat + i)
}

// actions
const doUpdate = () => {
  if (!props.site || !props.year || !props.cat || !props.accountNO) return

  showLoading.value = true

  axiosGet(
    '/Data/FinancialBalance' +
      props.cat +
      '?Site=' +
      props.site +
      '&Year=' +
      props.year +
      '&AccountNO=' +
      props.accountNO
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
  _forEach(data, (value) => {
    value.AccountAndCurrency = value.AccountNO + value.Currency
  })
  lengend = _uniq(_map(data, 'AccountAndCurrency'))
  dataByLengend = _groupBy(data, 'AccountAndCurrency')
  series = []
  _forEach(lengend, (value, index) => {
    const data12 = []
    for (let i = 0; i <= 12; i++) {
      // key C1,D1,M1,B1
      const key = props.cat + i
      data12.push(dataByLengend[value][0][key])
    }
    series[index] = {
      type: 'bar',
      name: value,
      tooltip: {
        trigger: 'item'
      },
      data: data12
    }
  })
}

const setEchart = () => {
  // data is ready,set echart option
  eChart.setOption(
    {
      title: {
        text: catText,
        left: 'center'
      },
      legend: {
        data: lengend,
        left: 10,
        top: 20,
        itemWidth: 10,
        itemHeight: 10,
        textStyle: { fontSize: 10 }
      },
      toolbox: defaultToolbox(
        dimensions,
        data,
        'Account ' + props.accountNO + ' ' + catText + ' of ' + props.year
      ),
      tooltip: defaultTooltip,
      xAxis: {
        type: 'category',
        data: [
          'Open',
          'Jan',
          'Feb',
          'Mar',
          'Apr',
          'May',
          'Jun',
          'Jul',
          'Aug',
          'Sep',
          'Oct',
          'Nov',
          'Dec'
        ]
      },
      yAxis: {
        type: 'value',
        axisLabel: {
          formatter: function (value) {
            if (value >= 1000000) {
              value = value / 1000000 + 'M'
            } else if (value >= 1000 && value < 1000000) {
              value = value / 1000 + 'K'
            } else if (value <= -1000 && value > -1000000) {
              value = value / 1000 + 'K'
            } else if (value <= -1000000) {
              value = value / 1000000 + 'M'
            } else {
              // no change
            }
            return value
          }
        }
      },
      series: series,
      grid: { bottom: 30 }
    },
    true
  )
}

const resize = () => {
  eChart.resize()
}

// events
onMounted(() => {
  eChart = echarts.init(document.getElementById(eChartId))
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
  () => [props.site, props.year, props.cat, props.accountNO],
  (...newAndold) => {
    // newAndold[1]:old
    // newAndold[0]:new
    console.debug('watch:' + newAndold[1] + ' ---> ' + newAndold[0])

    doUpdate()
  }
)
</script>
