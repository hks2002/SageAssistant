<template>
  <q-item>
    <div
      id="EchartSupplierDelayHistory"
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
import { notifyError } from 'assets/common'
import { useI18n } from 'vue-i18n'
import { axios } from 'boot/axios'

import {
  echarts,
  defaultTooltip,
  defaultToolbox,
  defaultLegend,
  defaultDataZoom,
  jsonToMultLine
} from 'assets/echartsCfg.js'

import _groupBy from 'lodash/groupBy'
import _forEach from 'lodash/forEach'
import _uniq from 'lodash/uniq'
import _map from 'lodash/map'

export default defineComponent({
  name: 'EchartSupplierDelayHistory',

  props: {
    supplierCode: String,
    dateFrom: String,
    dateTo: String
  },
  setup(props, ctx) {
    const { t } = useI18n({ useScope: 'global' })

    let eChart = null
    let data = []
    let lengend = []
    let dataByLengend = []
    const dataset = []
    const series = []
    const dimensions = [
      'Site',
      'SupplierCode',
      'PurchaseNO',
      'ProjectNO',
      'PN',
      'Description',
      'AckDate',
      'ExpectDate',
      'OrderDate',
      'ReceiptNO',
      'ReceiptDate',
      'DaysDelay'
    ]
    const showLoading = ref(false)

    const doUpdate = () => {
      showLoading.value = true

      axios.get('/Data/SupplierDelayHistory?SupplierCode=' + props.supplierCode + '&DateFrom=' + props.dateFrom + '&DateTo=' + props.dateTo)
        .then((response) => {
          data = response.data
          prepareData()
          setEchart()
        })
        .catch((e) => {
          console.error(e)
          notifyError(t('Loading Supplier Delay History Failed!'))
        }).finally(() => {
          showLoading.value = false
        })
    }

    const prepareData = () => {
      lengend = _uniq(_map(data, 'Site'))
      dataByLengend = _groupBy(data, 'Site')

      _forEach(lengend, (value, index) => {
        // dataset
        dataset[index] = { source: dataByLengend[value] }
        // series
        series[index] = {
          type: 'scatter',
          name: value,
          datasetIndex: index,
          symbolSize: 5,
          tooltip: {
            trigger: 'item',
            formatter: (params, ticket, callback) => {
              return jsonToMultLine(dimensions, params.data)
            }
          },
          dimensions: dimensions,
          encode: {
            x: 'ReceiptDate',
            y: 'DaysDelay'
          }
        }
      })
    }

    const setEchart = () => {
      // data is ready,set echart option
      eChart.setOption({
        title: {
          text: t('Delay History') + '(' + props.dateFrom + '-->' + props.dateTo + ')',
          subtext: '',
          left: 'center'
        },
        legend: defaultLegend,
        toolbox: defaultToolbox(dimensions, data, t('Delay History') + '(' + props.dateFrom + '-->' + props.dateTo + ')'),
        tooltip: defaultTooltip,
        dataZoom: defaultDataZoom(),
        xAxis: {
          type: 'time',
          name: 'Except'
        },
        yAxis: {
        },
        dataset: dataset,
        series: series
      })
    }

    onMounted(() => {
      console.debug('onMounted EchartSupplierDelayHistory')
      eChart = echarts.init(document.getElementById('EchartSupplierDelayHistory'))
      if (props.supplierCode) {
        doUpdate()
      }
    })

    // Don't use watchEffect, it run before Mounted.
    watch(() => [props.supplierCode, props.dateFrom, props.dateTo], (...newAndold) => {
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
