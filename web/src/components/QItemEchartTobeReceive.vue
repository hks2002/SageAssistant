<template>
  <q-item id="EchartTobeReceive">
  </q-item>
</template>
<script>
if (process.env.DEV) {
  require('../mock/TobeReceive')
}

const echarts = require('echarts/lib/echarts')
require('echarts/lib/chart/scatter')
require('echarts/lib/chart/line')
require('echarts/lib/component/tooltip')
require('echarts/lib/component/toolbox')
require('echarts/lib/component/dataZoom')
require('echarts/lib/component/legend')
require('echarts/lib/component/title')

import { EchartColors } from '../js/EchartColors'
import { jsonToExcel, jsonToTable, jsonToMultLine } from '../js/jsonTool'
import { date } from 'quasar'

const _map = require('lodash/map')
const _uniq = require('lodash/uniq')
const _groupBy = require('lodash/groupBy')
const _forEach = require('lodash/forEach')

export default {
  name: 'QItemEchartTobeReceive',
  props: {
    site: String
  },
  data () {
    return {
      eChart: null,
      data: [],
      lengend: [],
      dataByLengend: [],
      dataset: [],
      series: [],
      dimensions: [
        'ProjectNO',
        'PurchaseNO',
        'Line',
        'VendorCode',
        'VendorName',
        'PN',
        'Qty',
        'Unit',
        'Description',
        'NetPrice',
        'Currency',
        'RMB',
        'Rate',
        'AckDate',
        'ExpectDate',
        'OrderDate',
        'CreateUser',
        'DaysLeft'
      ]
    }
  },
  methods: {
    doUpdate (site) {
      this.$axios.get('/Data/TobeReceive?site=' + site)
        .then((response) => {
          console.debug('[axios] ' + response.status + ' ' + response.statusText + ' ' + response.config.url)
          console.debug(JSON.stringify(response.data))

          this.data = response.data
          const newDate = new Date()
          this.data.forEach((row) => {
            row.DaysLeft = date.getDateDiff(row.ExpectDate, newDate, 'days')
          })
          this.lengend = _uniq(_map(this.data, 'CreateUser'))
          this.dataByLengend = _groupBy(this.data, 'CreateUser')
          console.debug(JSON.stringify(this.dataByLengend))

          this.eChart.hideLoading()
          this.setEchartScatter()
          this.setEchart()
        })
        .catch((e) => {
          console.error(e)
          this.$q.notify({
            color: 'negative',
            position: 'top',
            message: 'Loading TobeReceive Failed!',
            icon: 'fas fa-exclamation-triangle'
          })
        })
    },

    showLoading () {
      if (this.eChart) {
        this.eChart.showLoading({
          text: 'Loading'
        })
      }
    },

    doReset () {
      this.data = []
      if (this.eChart) {
        this.eChart.clear()
        this.data = []
        this.lengend = []
        this.dataByLengend = []
        this.dataset = []
        this.series = []
      }
    },

    setEchartScatter () {
      // create echarts dataset/series by sites for scatter begin >>>
      _forEach(this.lengend, (value, index) => {
        // dataset
        console.debug(this.dataByLengend[value])
        this.dataset[index] = { source: this.dataByLengend[value] }
        // series
        this.series[index] = {
          type: 'scatter',
          name: value,
          datasetIndex: index,
          dimensions: this.dimensions,
          label: {
            show: true,
            position: 'bottom',
            formatter: '{@ProjectNO}'
          },
          tooltip: {
            trigger: 'item',
            formatter: (params, ticket, callback) => {
              return jsonToMultLine(this.dimensions, params.data)
            }
          },
          symbolSize: function (data) {
            return Math.pow(Math.sqrt(data.RMB), 1 / 3) * Math.E
          },
          encode: {
            x: 'ExpectDate',
            y: 'DaysLeft'
          }
        }
      })
    },

    setEchart () {
      this.eChart.setOption({
        color: EchartColors,
        title: {
          text: 'Bom to be Receive',
          subtext: 'Currency Rate Data From State Administration of Foreign Exchange',
          left: 'center'
        },
        legend: { left: 10, top: 20 },
        toolbox: {
          feature: {
            dataView: {
              title: 'DataView',
              optionToContent: () => {
                return jsonToTable(this.dimensions, this.data, 'Bom to be Receive')
              }
            },
            myTool: {
              show: true,
              title: 'Downlaod',
              icon: 'path://M4.7,22.9L29.3,45.5L54.7,23.4M4.6,43.6L4.6,58L53.8,58L53.8,43.6M29.2,45.1L29.2,0',
              onclick: () => {
                const timeStamp = Date.now()
                const formattedString = date.formatDate(timeStamp, 'YYYY-MM-DD')
                jsonToExcel(this.dimensions, this.data, 'BomTobeReceive' + formattedString)
              }
            }
          }
        },
        tooltip: {},
        xAxis: {
          type: 'time',
          minInterval: 3600 * 24 * 1000 // 最小刻度1天
        },
        yAxis: [{
          type: 'value',
          // min: 10,
          axisLabel: {
            formatter: '{value}\nDay'
          }
        }],
        dataZoom: [{
          type: 'slider',
          xAxisIndex: [0]
        },
        {
          type: 'slider',
          yAxisIndex: [0],
          left: '93%'
        },
        {
          type: 'inside',
          xAxisIndex: [0]
        },
        {
          type: 'inside',
          yAxisIndex: [0]
        }],
        dataset: this.dataset,
        series: this.series
      })
    }
  },
  watch: { // run before mounted
    site: {
      handler (newVal, oldVal) {
        console.debug(oldVal + ' --> ' + newVal)

        if (newVal) {
          this.doReset()
          this.showLoading()
          this.doUpdate(newVal)
        } else {
          this.doReset()
        }
      },
      immediate: true
    }
  },
  mounted: function () {
    this.eChart = echarts.init(document.getElementById('EchartTobeReceive'))
    this.showLoading()
  }
}
</script>
