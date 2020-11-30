<template>
  <q-item id="EchartSupplyOpenAmount">
  </q-item>
</template>
<script>
if (process.env.DEV) {
  require('../mock/SupplyOpenAmount')
}

const echarts = require('echarts/lib/echarts')
require('echarts/lib/chart/bar')
require('echarts/lib/component/tooltip')
require('echarts/lib/component/toolbox')
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
  name: 'QItemEchartSupplyOpenAmount',
  props: {
    supplyCode: String
  },
  data () {
    return {
      eChart: null,
      lengend: [],
      dataByLengend: [],
      sites: [],
      dataset: [],
      series: [],
      dimensions: [
        'Site',
        'SupplyCode',
        'Amount',
        'Currency',
        'USD',
        'Rate'
      ]
    }
  },
  methods: {
    doUpdate (supplyCode) {
      this.$axios.get('/Data/SupplyOpenAmount?SupplyCode=' + supplyCode)
        .then((response) => {
          console.debug('[axios] ' + response.status + ' ' + response.statusText + ' ' + response.config.url)
          console.debug(JSON.stringify(response.data))

          this.data = response.data

          this.lengend = _uniq(_map(this.data, 'Currency'))
          this.dataByLengend = _groupBy(this.data, 'Currency')
          this.sites = _uniq(_map(this.data, 'Site'))

          this.eChart.hideLoading()
          this.setBar()
          this.setEchart()
        })
        .catch((e) => {
          console.error(e)
          this.$q.notify({
            color: 'negative',
            position: 'top',
            message: 'Loading Supply Open Amount Failed!',
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
        this.sites = []
      }
    },

    setBar () {
      // create echarts dataset/series by sites for scatter begin >>>
      _forEach(this.lengend, (value, index) => {
        // dataset
        this.dataset[index] = { source: this.dataByLengend[value] }
        // series
        this.series[index] = {
          type: 'bar',
          name: value,
          stack: 'One',
          datasetIndex: index,
          label: {
            show: true,
            position: 'inside'
          },
          tooltip: {
            trigger: 'item',
            formatter: (params, ticket, callback) => {
              return jsonToMultLine(this.dimensions, params.data)
            }
          },
          dimensions: this.dimensions,
          encode: {
            x: 'Site',
            y: 'USD'
          }
        }
      })
    },

    setEchart () {
      this.eChart.setOption({
        color: EchartColors,
        title: {
          text: 'Open Amount(All)',
          subtext: '',
          left: 'center'
        },
        legend: { left: 10, top: 20 },
        toolbox: {
          feature: {
            dataView: {
              optionToContent: () => {
                return jsonToTable(this.dimensions, this.data, 'Supply Open Amount')
              }
            },
            myTool: {
              show: true,
              title: 'Downlaod Data',
              icon: 'path://M4.7,22.9L29.3,45.5L54.7,23.4M4.6,43.6L4.6,58L53.8,58L53.8,43.6M29.2,45.1L29.2,0',
              onclick: () => {
                const timeStamp = Date.now()
                const formattedString = date.formatDate(timeStamp, 'YYYY-MM-DD')
                jsonToExcel(this.dimensions, this.data, 'Supply Open Amount' + formattedString)
              }
            }
          }
        },
        tooltip: {
        },
        dataset: this.dataset,
        xAxis: {
          type: 'category',
          data: this.sites
        },
        yAxis: {
          type: 'value',
          name: 'USD',
          nameLocation: 'start',
          axisLabel: {
            formatter: '{value}'
          }
        },
        series: this.series
      })
    }
  },
  watch: { // run before mounted
    supplyCode: {
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
    this.eChart = echarts.init(document.getElementById('EchartSupplyOpenAmount'))
  }
}
</script>
