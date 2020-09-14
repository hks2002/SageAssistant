<template>
  <q-item id="EchartCostHistory">
  </q-item>
</template>
<script>
if (process.env.DEV) {
  require('../mock/CostHistory')
}

let echarts = require('echarts/lib/echarts')
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
let _sumBy = require('lodash/sumBy')

export default {
  name: 'QItemEchartCostHistory',
  props: {
    pnRoot: String
  },
  data () {
    return {
      eChart: null,
      costHistory: [],
      lengend: [],
      dataByLengend: [],
      dataBySiteProject: [],
      dataSumBySiteProject: [],
      dataset: [],
      series: [],
      dimensions: [
        'PurchaseSite',
        'ProjectNO',
        'OrderPN',
        'OrderDate',
        'PurchaseNO',
        'Line',
        'VendorCode',
        'VendorName',
        'PurchasePN',
        'Description',
        'Qty',
        'Currency',
        'NetPrice',
        'RMB',
        'Rate'
      ],
      miniDemensions: [
        'PurchaseSite',
        'ProjectNO',
        'OrderPN',
        'OrderDate',
        'RMB']
    }
  },
  methods: {
    doUpdate (pnRoot) {
      this.$axios.get('/Data/CostHistory?PnRoot=' + pnRoot)
        .then((response) => {
          console.debug('[axios] ' + response.status + ' ' + response.statusText + ' ' + response.config.url)
          console.debug(JSON.stringify(response.data))

          this.data = response.data
          this.dataBySiteProject = _groupBy(this.data, function (n) { return n.PurchaseSite + n.ProjectNO })
          _forEach(this.dataBySiteProject, (value, index, array) => {
            this.dataSumBySiteProject.push({
              PurchaseSite: value[0]['PurchaseSite'],
              ProjectNO: value[0]['ProjectNO'],
              OrderPN: value[0]['OrderPN'],
              OrderDate: value[0]['OrderDate'],
              RMB: _sumBy(value, 'RMB')
            })
          })
          this.lengend = _uniq(_map(this.data, 'PurchaseSite'))
          this.dataByLengend = _groupBy(this.dataSumBySiteProject, 'PurchaseSite')

          this.eChart.hideLoading()
          this.setEchartLine()
          this.setEchart()
        })
        .catch((e) => {
          console.error(e)
          this.$q.notify({
            color: 'negative',
            position: 'top',
            message: 'Loading Cost History Failed!',
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
      this.costHistory = []
      if (this.eChart) {
        this.eChart.clear()
        this.costHistory = []
        this.lengend = []
        this.dataByLengend = []
        this.dataBySiteProject = []
        this.dataSumBySiteProject = []
        this.dataset = []
        this.series = []
      }
    },

    setEchartLine () {
      // create echarts dataset/series by sites for scatter begin >>>
      _forEach(this.lengend, (value, index) => {
        // dataset
        this.dataset[index] = { source: this.dataByLengend[value] }
        // series
        this.series[index] = {
          type: 'line',
          name: value,
          datasetIndex: index,
          label: {
            show: true,
            position: 'bottom',
            formatter: '{@RMB} RMB'
          },
          tooltip: {
            trigger: 'item',
            formatter: (params, ticket, callback) => {
              return jsonToMultLine(this.miniDemensions, params.data)
            }
          },
          dimensions: this.miniDemensions,
          encode: {
            x: 'OrderDate',
            y: 'RMB'
          }
        }
      })
    },

    setEchart () {
      this.eChart.setOption({
        color: EchartColors,
        title: {
          text: 'Sales Order Cost History',
          subtext: 'Currency Rate Data From State Administration of Foreign Exchange',
          left: 'center'
        },
        legend: { left: 10, top: 20 },
        grid: [
          { left: '5%', right: '50%' }
        ],
        toolbox: {
          feature: {
            dataView: {
              optionToContent: () => {
                return jsonToTable(this.dimensions, this.data, 'Cost History')
              }
            },
            myTool: {
              show: true,
              title: 'Downlaod Data',
              icon: 'path://M4.7,22.9L29.3,45.5L54.7,23.4M4.6,43.6L4.6,58L53.8,58L53.8,43.6M29.2,45.1L29.2,0',
              onclick: () => {
                let timeStamp = Date.now()
                let formattedString = date.formatDate(timeStamp, 'YYYY-MM-DD')
                jsonToExcel(this.dimensions, this.costHistory, 'CostHistory' + formattedString)
              }
            }
          }
        },
        tooltip: {},
        dataset: this.dataset,
        dataZoom: [{
          start: 0,
          end: 100
        }, {
          type: 'inside'
        }],
        xAxis: {
          type: 'time'
        },
        yAxis: {},
        series: this.series
      })
    }
  },
  watch: { // run before mounted
    pnRoot: {
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
    this.eChart = echarts.init(document.getElementById('EchartCostHistory'))
    // this.showLoading()
  }
}
</script>
