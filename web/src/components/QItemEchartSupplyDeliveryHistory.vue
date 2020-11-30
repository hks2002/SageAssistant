<template>
  <q-item id="EchartSupplyDeliveryHistory">
  </q-item>
</template>
<script>
if (process.env.DEV) {
  require('../mock/SupplyDeliveryHistory')
}

const echarts = require('echarts/lib/echarts')
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
  name: 'QItemEchartSupplyDeliveryHistory',
  props: {
    supplyCode: String,
    dateFrom: String,
    dateTo: String
  },
  data () {
    return {
      eChart: null,
      lengend: [],
      dataByLengend: [],
      dataset: [],
      series: [],
      dimensions: [
        'Site',
        'SupplyCode',
        'PurchaseNO',
        'ProjectNO',
        'PN',
        'Description',
        'AckDate',
        'ExpectDate',
        'OrderDate',
        'ReceiptNO',
        'ReceiptDate',
        'DaysNeed'
      ]
    }
  },
  methods: {
    doUpdate (supplyCode, dateFrom, dateTo) {
      this.$axios.get('/Data/SupplyDeliveryHistory?SupplyCode=' + supplyCode + '&DateFrom=' + dateFrom + '&DateTo=' + dateTo)
        .then((response) => {
          console.debug('[axios] ' + response.status + ' ' + response.statusText + ' ' + response.config.url)
          console.debug(JSON.stringify(response.data))

          this.data = response.data

          this.lengend = _uniq(_map(this.data, 'Site'))
          this.dataByLengend = _groupBy(this.data, 'Site')

          this.eChart.hideLoading()
          this.setLine()
          this.setEchart()
        })
        .catch((e) => {
          console.error(e)
          this.$q.notify({
            color: 'negative',
            position: 'top',
            message: 'Loading Supply Delivery History Failed!',
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
        this.lengend = []
        this.dataByLengend = []
        this.dataset = []
        this.series = []
      }
    },

    setLine () {
      // create echarts dataset/series by sites for scatter begin >>>
      _forEach(this.lengend, (value, index) => {
        // dataset
        this.dataset[index] = { source: this.dataByLengend[value] }
        // series
        this.series[index] = {
          type: 'line',
          name: value,
          datasetIndex: index,
          lineStyle: {
            width: 1
          },
          symbolSize: 1,
          animation: false,
          areaStyle: {},
          tooltip: {
            trigger: 'item',
            formatter: (params, ticket, callback) => {
              return jsonToMultLine(this.dimensions, params.data)
            }
          },
          dimensions: this.dimensions,
          encode: {
            x: 'ReceiptDate',
            y: 'DaysNeed'
          }
        }
      })
    },

    setEchart () {
      this.eChart.setOption({
        color: EchartColors,
        title: {
          text: 'Delivery History(' + this.dateFrom + '-->' + this.dateTo + ')',
          subtext: '',
          left: 'center'
        },
        legend: { left: 10, top: 20 },
        toolbox: {
          feature: {
            dataView: {
              optionToContent: () => {
                return jsonToTable(this.dimensions, this.data, 'Supply Delivery History')
              }
            },
            myTool: {
              show: true,
              title: 'Downlaod Data',
              icon: 'path://M4.7,22.9L29.3,45.5L54.7,23.4M4.6,43.6L4.6,58L53.8,58L53.8,43.6M29.2,45.1L29.2,0',
              onclick: () => {
                const timeStamp = Date.now()
                const formattedString = date.formatDate(timeStamp, 'YYYY-MM-DD')
                jsonToExcel(this.dimensions, this.data, 'Supply Delivery History' + formattedString)
              }
            }
          }
        },
        tooltip: {
        },
        dataZoom: [{
          height: 15,
          bottom: '5px'
        },
        {
          type: 'inside'
        }],
        dataset: this.dataset,
        xAxis: {
          type: 'time',
          name: 'Receipt'
        },
        yAxis: {
          type: 'value',
          axisLabel: {
            formatter: '{value}'
          }
        },
        series: this.series
      })
    }
  },
  watch: {
    supplyCode: {
      handler (newVal, oldVal) {
        console.debug(oldVal + ' --> ' + newVal)

        if (newVal) {
          this.doUpdate(newVal, this.dateFrom, this.dateTo)
        } else {
          this.doReset()
        }
      },
      immediate: true
    },
    dateFrom: {
      handler (newVal, oldVal) {
        console.debug(oldVal + ' --> ' + newVal)

        if (this.supplyCode) {
          this.doUpdate(this.supplyCode, newVal, this.dateTo)
        } else {
          this.doReset()
        }
      },
      immediate: true
    },
    dateTo: {
      handler (newVal, oldVal) {
        console.debug(oldVal + ' --> ' + newVal)

        if (this.supplyCode) {
          this.doUpdate(this.supplyCode, this.dateFrom, newVal)
        } else {
          this.doReset()
        }
      },
      immediate: true
    }
  },
  mounted: function () {
    this.eChart = echarts.init(document.getElementById('EchartSupplyDeliveryHistory'))
  }
}
</script>
