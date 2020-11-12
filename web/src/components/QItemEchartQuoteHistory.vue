<template>
  <q-item id="EchartQuoteHistory">
  </q-item>
</template>
<script>
if (process.env.DEV) {
  require('../mock/QuoteHistory')
  require('../mock/CurrencyRate')
}

let echarts = require('echarts/lib/echarts')
require('echarts/lib/chart/pie')
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
  name: 'QItemEchartQuoteHistory',
  props: {
    pnRoot: String
  },
  data () {
    return {
      eChart: null,
      data: [],
      lengend: [],
      dataByLengend: [],
      dataCountedByLengend: [],
      dataset: [],
      series: [],
      dimensions: [
        'SalesSite',
        'QuoteNO',
        'QuoteDate',
        'PN',
        'Qty',
        'Currency',
        'NetPrice',
        'USD',
        'Rate',
        'CustomerCode',
        'CustomerName',
        'OrderFlag',
        'OrderNO'
      ],
      dataZoomStartValue: '1900-01-01'
    }
  },
  methods: {
    doUpdate (pnRoot) {
      this.$axios.get('/Data/QuoteHistory?PnRoot=' + pnRoot)
        .then((response) => {
          console.info('[axios] ' + response.status + ' ' + response.statusText + ' ' + response.config.url)
          console.debug(JSON.stringify(response.data))

          this.data = response.data
          let len = this.data.length
          if (len >= 20) {
            this.dataZoomStartValue = this.data[len - 20].QuoteDate
          } else {
            this.dataZoomStartValue = this.data[0].QuoteDate
          }
          this.lengend = _uniq(_map(this.data, 'SalesSite'))
          this.dataByLengend = _groupBy(this.data, 'SalesSite')

          _forEach(this.dataByLengend, (value, index, array) => {
            this.dataCountedByLengend.push({ SalesSite: value[0]['SalesSite'], Qty: _sumBy(value, 'Qty') })
          })

          this.eChart.hideLoading()
          this.setEchartLine()
          this.setEchartPie()
          this.setEchart()
        })
        .catch((e) => {
          console.error(e)
          this.$q.notify({
            color: 'negative',
            position: 'top',
            message: 'Loading Quote History Failed!',
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
        this.dataCountedByLengend = []
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
            formatter: '{@NetPrice} {@Currency}'
          },
          tooltip: {
            trigger: 'item',
            formatter: (params, ticket, callback) => {
              return jsonToMultLine(this.dimensions, params.data)
            }
          },
          symbolSize: function (data) {
            console.debug(data.OrderNO.length)
            // don't compair length to 0, maybe " "
            return data.OrderNO.length > 3 ? 12 : 4
          },
          dimensions: this.dimensions,
          encode: {
            x: 'QuoteDate',
            y: 'USD'
          }
        }
      })
    },

    setEchartPie () {
      this.dataset.push({ source: this.dataCountedByLengend })

      let seriesBySite = {
        type: 'pie',
        datasetIndex: this.dataset.length - 1,
        center: ['88%', '50%'],
        radius: [0, '50%'],
        label: {
          formatter: '{@SalesSite} \nQty:{@Qty}\n{d}%'
        },
        tooltip: {
          trigger: 'item',
          formatter: (params, ticket, callback) => {
            let s = ''
            s += params.data['SalesSite'] + '<br/>'
            s += 'Qty:' + params.data['Qty'] + '<br/>'
            return s
          }
        },
        labelLine: {
          normal: {
            show: true
          }
        },
        dimensions: ['SalesSite', 'Qty'],
        encode: {
          value: 'Qty'
        }
      }

      this.series.push(seriesBySite)
    },

    setEchart () {
      this.eChart.setOption({
        color: EchartColors,
        title: {
          text: 'Quote History',
          subtext: 'Currency Rate Data From State Administration of Foreign Exchange',
          left: 'center'
        },
        legend: { left: 10, top: 20 },
        grid: [
          { left: '5%', right: '25%' }
        ],
        toolbox: {
          feature: {
            dataView: {
              optionToContent: () => {
                return jsonToTable(this.dimensions, this.data, 'Quote History')
              }
            },
            myTool: {
              show: true,
              title: 'Downlaod Data',
              icon: 'path://M4.7,22.9L29.3,45.5L54.7,23.4M4.6,43.6L4.6,58L53.8,58L53.8,43.6M29.2,45.1L29.2,0',
              onclick: () => {
                let timeStamp = Date.now()
                let formattedString = date.formatDate(timeStamp, 'YYYY-MM-DD')
                jsonToExcel(this.dimensions, this.data, 'QuoteHistory' + formattedString)
              }
            }
          }
        },
        tooltip: {
          position: 'right'
        },
        dataset: this.dataset,
        dataZoom: [{
          height: 15,
          bottom: '5px',
          startValue: this.dataZoomStartValue,
          start: 0,
          end: 100
        }, {
          type: 'inside'
        }],
        xAxis: {
          type: 'time'
        },
        yAxis: {
          gridIndex: 0,
          axisLabel: {
            formatter: '{value}\nUSD'
          }
        },
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
    this.eChart = echarts.init(document.getElementById('EchartQuoteHistory'))
    // this.showLoading()
  }
}
</script>
