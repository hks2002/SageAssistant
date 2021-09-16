<template>
  <q-item>
    <div
      id="EchartTodoClosedWO"
      style="height:100%; width:100%"
    />
    <q-inner-loading :showing="showLoading">
      <q-spinner-ios
        size="50px"
        color="primary"
      />
    </q-inner-loading>
  </q-item>
</template>

<script>
import { defineComponent, onMounted, ref, watch } from 'vue'
import { date } from 'quasar'
import { notifyError } from 'assets/common'
import { useI18n } from 'vue-i18n'
import { axios } from 'boot/axios'

import {
  echarts,
  defaultXAxisTime,
  defaultTooltip,
  defaultToolbox,
  defaultLegend,
  defaultScatterSerial
} from 'assets/echartsCfg.js'

import _groupBy from 'lodash/groupBy'
import _forEach from 'lodash/forEach'
import _sortBy from 'lodash/sortBy'
import _map from 'lodash/map'
import _uniq from 'lodash/uniq'

export default defineComponent({
  name: 'EchartTodoClosedWO',
  props: {
    site: String
  },
  setup (props, ctx) {
    const { t } = useI18n({ useScope: 'global' })

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
    const showLoading = ref(false)

    const doUpdate = () => {
      showLoading.value = true

      axios.get('/Data/TobeClosedWO?Site=' + props.site)
        .then((response) => {
          data = response.data
          prepareData()
          setEchart()
        })
        .catch((e) => {
          console.error(e)
          notifyError(t('Loading TobeClosedWO Failed!'))
        }).finally(() => {
          showLoading.value = false
        })
    }
    const prepareData = () => {
      data = _sortBy(data, ['WorkOrderNO'])
      lengend = _uniq(_map(data, 'ProductionStatus'))
      dataByLengend = _groupBy(data, 'ProductionStatus')
      dataset = []
      series = []

      _forEach(lengend, (value, index) => {
        dataset[index] = { source: dataByLengend[value] }
        series[index] = defaultScatterSerial(index, value, '{@WorkOrderNO}', dimensions, 'OrderDate', 'WorkOrderNO')
      })
    }

    const setEchart = () => {
      // data is ready,set echart option
      eChart.setOption({
        title: {
          text: t('Sales order line is closed but it\'s workorder product item is still open at ') + props.site,
          left: 'center'
        },
        legend: defaultLegend,
        toolbox: defaultToolbox(dimensions, data, t('Sales order line is closed but it\'s workorder product item is still open at ') + props.site),
        tooltip: defaultTooltip,
        xAxis: defaultXAxisTime,
        grid: [
          { left: '5%', right: '5%' }
        ],
        yAxis: [{
          type: 'category',
          show: false
        }],
        dataZoom: [{
          type: 'slider',
          xAxisIndex: [0],
          height: 15
        },
        {
          type: 'slider',
          yAxisIndex: [0]
        }],
        dataset: dataset,
        series: series
      }, true)
    }

    onMounted(() => {
      console.debug('onMounted EchartTodoClosedWO')
      echarts.init(document.getElementById('EchartTodoClosedWO')).dispose()
      eChart = echarts.init(document.getElementById('EchartTodoClosedWO'))

      if (props.site) {
        doUpdate()
      }
    })

    // Don't use watchEffect, it run before Mounted.
    watch(() => [props.site], (...newAndold) => {
      // newAndold[1]:old
      // newAndold[0]:new
      console.debug('watch:' + newAndold[1] + ' ---> ' + newAndold[0])
      if (newAndold[0][0]) {
        doUpdate()
      }
    })
    return {
      showLoading
    }
  }
})
</script>
