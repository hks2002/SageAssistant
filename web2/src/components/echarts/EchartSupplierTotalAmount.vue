<template>
  <q-item>
    <div
      id="EchartSupplierTotalAmount"
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
  defaultTooltip,
  defaultToolbox,
  defaultLegend,
  defaultBarStackedSerial
} from 'assets/echartsCfg.js'

import _groupBy from 'lodash/groupBy'
import _forEach from 'lodash/forEach'
import _uniq from 'lodash/uniq'
import _map from 'lodash/map'

export default defineComponent({
  name: 'EchartSupplierTotalAmount',

  props: {
    supplierCode: String,
    dateFrom: String,
    dateTo: String
  },

  setup (props, ctx) {
    const { t } = useI18n({ useScope: 'global' })

    let eChart = null
    let data = []
    let lengend = []
    let dataByLengend = []
    let sites = []
    let dataset = []
    let series = []
    const dimensions = [
      'Site',
      'SupplierCode',
      'Amount',
      'Currency',
      'USD',
      'Rate'
    ]
    const showLoading = ref(false)

    const doUpdate = () => {
      if (!date.isValid(props.dateFrom) || !date.isValid(props.dateTo)) {
        return
      }

      showLoading.value = true

      axios.get('/Data/SupplierTotalAmount?SupplierCode=' + props.supplierCode + '&DateFrom=' + props.dateFrom + '&DateTo=' + props.dateTo)
        .then((response) => {
          data = response.data
          prepareData()
          setEchart()
        })
        .catch((e) => {
          console.error(e)
          notifyError(t('Loading Supplier Total Amount Failed!'))
        }).finally(() => {
          showLoading.value = false
        })
    }

    const prepareData = () => {
      lengend = _uniq(_map(data, 'Currency'))
      dataByLengend = _groupBy(data, 'Currency')
      sites = _uniq(_map(data, 'Site'))
      dataset = []
      series = []

      _forEach(lengend, (value, index) => {
        dataset[index] = { source: dataByLengend[value] }
        series[index] = defaultBarStackedSerial(index, value, '{@USD}', dimensions, 'Site', 'USD')
      })
    }

    const setEchart = () => {
      // data is ready,set echart option
      eChart.setOption({
        title: {
          text: t('Total Amount') + '(' + props.dateFrom + '-->' + props.dateTo + ')',
          subtext: '',
          left: 'center'
        },
        legend: defaultLegend,
        toolbox: defaultToolbox(dimensions, data, t('Total Amount') + '(' + props.dateFrom + '-->' + props.dateTo + ')'),
        tooltip: defaultTooltip,
        xAxis: {
          type: 'category',
          data: sites
        },
        yAxis: {
          type: 'value',
          name: 'USD',
          nameLocation: 'start',
          axisLabel: {
            formatter: '{value}'
          }
        },
        dataset: dataset,
        series: series
      }, true)
    }

    onMounted(() => {
      console.debug('onMounted EchartSupplierTotalAmount')
      eChart = echarts.init(document.getElementById('EchartSupplierTotalAmount'))
      if (props.supplierCode) {
        doUpdate()
      }
    })

    // Don't use watchEffect, it run before Mounted.
    watch(() => [props.supplierCode, props.dateFrom, props.dateTo], ([newVal0, newVal1, newVal2], [oldVal0, oldVal1, oldVal2]) => {
      console.debug('watch:' + oldVal0 + ' ---> ' + newVal0)
      console.debug('watch:' + oldVal1 + ' ---> ' + newVal1)
      console.debug('watch:' + oldVal2 + ' ---> ' + newVal2)
      if (newVal0) {
        doUpdate()
      }
    })

    return {
      showLoading
    }
  }
})
</script>
