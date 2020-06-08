<template>
  <q-item id="EchartTobeDelivery">
  </q-item>
</template>
<script>
if (process.env.DEV) {
  require('../mock/TobeDelivery')
}

let echarts = require('echarts/lib/echarts')
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

let _map = require('lodash/map')
let _uniq = require('lodash/uniq')
let _groupBy = require('lodash/groupBy')
let _forEach = require('lodash/forEach')

export default {
  name: 'QItemEchartTobeDelivery',
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
        'OrderType',
        'PN',
        'Qty',
        'Description',
        'CustomerCode',
        'CustomerName',
        'Currency',
        'NetPrice',
        'RMB',
        'Rate',
        'OrderDate',
        'RequestDate',
        'DaysLeft'
      ]
    }
  },
  methods: {
    doUpdate (site) {
      this.$axios.get('/Data/TobeDelivery?site=' + site)
        .then((response) => {
          console.debug('[axios] ' + response.status + ' ' + response.statusText + ' ' + response.config.url)
          console.debug(JSON.stringify(response.data))

          this.data = response.data
          let newDate = new Date()
          this.data.forEach((row) => {
            row.DaysLeft = date.getDateDiff(row.RequestDate, newDate, 'days')
          })
          this.lengend = _uniq(_map(this.data, 'OrderType'))
          this.dataByLengend = _groupBy(this.data, 'OrderType')
          console.debug(JSON.stringify(this.dataByLengend))

          this.setEchartScatter()
          this.setEchart()
        })
        .catch((e) => {
          console.error(e)
          this.$q.notify({
            color: 'negative',
            position: 'top',
            message: 'Loading TobeDelivery Failed!',
            icon: 'fas fa-exclamation-triangle'
          })
        })
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
          dimensions: this.dimensionsScatter,
          label: {
            show: true,
            position: 'bottom',
            formatter: '{@ProjectNO}'
          },
          tooltip: {
            position: 'top',
            trigger: 'item',
            formatter: (params, ticket, callback) => {
              return jsonToMultLine(this.dimensions, params.data)
            }
          },
          symbolSize: function (data) {
            return Math.pow(Math.sqrt(data['RMB']), 1 / 3) * Math.E
          },
          encode: {
            x: 'RequestDate',
            y: 'DaysLeft'
          }
        }
      })
    },

    setEchart () {
      this.eChart.setOption({
        color: EchartColors,
        title: {
          text: 'Production to be Delivery',
          subtext: 'Currency Rate Data From State Administration of Foreign Exchange',
          left: 'center'
        },
        legend: { left: 10, top: 20 },
        toolbox: {
          feature: {
            dataView: {
              title: 'DataView',
              optionToContent: () => {
                return jsonToTable(this.dimensions, this.data, 'Production to be Delivery')
              }
            },
            myTool: {
              show: true,
              title: 'Downlaod',
              icon: 'path://M4.7,22.9L29.3,45.5L54.7,23.4M4.6,43.6L4.6,58L53.8,58L53.8,43.6M29.2,45.1L29.2,0',
              onclick: () => {
                let timeStamp = Date.now()
                let formattedString = date.formatDate(timeStamp, 'YYYY-MM-DD')
                jsonToExcel(this.dimensions, this.data, 'TobeDelivery' + formattedString)
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
          start: 0,
          end: 100
        }, {
          type: 'inside'
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
          this.doUpdate(newVal)
        } else {
          this.doReset()
        }
      },
      immediate: true
    }
  },
  mounted: function () {
    this.eChart = echarts.init(document.getElementById('EchartTobeDelivery'))
  }
}
</script>
