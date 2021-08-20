<template>
  <q-item>
    <div
      id="EchartTodoDealWithOrderLine"
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
  name: 'EchartTodoDealWithOrderLine',
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
      'SalesOrderNO',
      'ProjectNO',
      'OrderType',
      'OrderCategory',
      'PN',
      'Qty',
      'Unit',
      'Description',
      'CustomerCode',
      'CustomerName',
      'OrderDate',
      'DemandDate',
      'Days'
    ]
    const showLoading = ref(false)

    const doUpdate = () => {
      showLoading.value = true

      axios.get('/Data/TobeDealWithOrderLine?site=' + props.site)
        .then((response) => {
          data = response.data
          prepareData()
          setEchart()
        })
        .catch((e) => {
          console.error(e)
          notifyError(t('Loading TobeDealWithOrderLine Failed!'))
        }).finally(() => {
          showLoading.value = false
        })
    }
    const prepareData = () => {
      const newDate = new Date()
      data.forEach((row) => {
        row.Days = date.getDateDiff(row.OrderDate, newDate, 'days')
      })
      data = _sortBy(data, ['Days'], ['desc'])

      lengend = _uniq(_map(data, 'OrderType'))
      dataByLengend = _groupBy(data, 'OrderType')
      dataset = []
      series = []

      _forEach(lengend, (value, index) => {
        dataset[index] = { source: dataByLengend[value] }
        series[index] = defaultScatterSerial(index, value, '{@ProjectNO}', dimensions, 'OrderDate', 'ProjectNO')
      })
    }

    const setEchart = () => {
      // data is ready,set echart option
      eChart.setOption({
        title: {
          text: t('New order line without purchasing or without workorder at ') + props.site,
          left: 'center'
        },
        legend: defaultLegend,
        toolbox: defaultToolbox(dimensions, data, t('New order line without purchasing or without workorder at ') + props.site),
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
      console.debug('onMounted EchartTodoDealWithOrderLine')
      echarts.init(document.getElementById('EchartTodoDealWithOrderLine')).dispose()
      eChart = echarts.init(document.getElementById('EchartTodoDealWithOrderLine'))

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
