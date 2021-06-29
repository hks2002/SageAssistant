const echarts = require('echarts/lib/echarts')
import {
  GridComponent,
  ToolboxComponent,
  DataZoomComponent,
  LegendComponent,
  TitleComponent,
  TooltipComponent
} from 'echarts/components'
import {
  BarChart,
  LineChart,
  PieChart,
  ScatterChart
} from 'echarts/charts'

echarts.use([
  GridComponent,
  ToolboxComponent,
  DataZoomComponent,
  LegendComponent,
  TitleComponent,
  TooltipComponent,
  BarChart,
  LineChart,
  PieChart,
  ScatterChart
])

import _cloneDeep from 'lodash/cloneDeep'
import _merge from 'lodash/merge'
import _get from 'lodash/get'

import { jsonToExcel, jsonToTable, jsonToMultLine } from 'assets/dataUtils.js'
import { date } from 'quasar'
// --------------------------------- default setting ------------------------------
const defaultSeriesColor = [
  '#40a9ff',
  '#f759ab',
  '#ffa940',
  '#bae637',
  '#9254de',
  '#36cfc9',
  '#ffec3d',
  '#ff7a45',
  '#597ef7',
  '#73d13d',
  '#ffc53d',
  '#ff4d4f',
  '#69c0ff',
  '#ff85c0',
  '#ffc069',
  '#d3f261',
  '#b37feb',
  '#5cdbd3',
  '#fff566',
  '#ff9c6e',
  '#85a5ff',
  '#95de64',
  '#ffd666',
  '#ff7875'
]

const defaultTooltip = {
  backgroundColor: 'rgba(255,255,255,0.8)',
  borderWidth: '1',
  extraCssText: 'box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);',
  textStyle: {
    fontStyle: 'normal',
    fontFamily: 'Arial',
    fontSize: 10,
    lineHeight: 10
  },
  confine: true
}

const defaultXAxisTime = {
  type: 'time',
  splitLine: {
    show: true
  },
  axisLabel: {
    show: true,
    showMaxLabel: true,
    showMinLabel: true,
    formatter: '{MM}-{dd}\n{yyyy}'
  }
}

const defaultYAxisUSD = {
  min: 0,
  max: function (value) {
    if (isNaN(value.max)) {
      return 1000
    } else {
      return null
    }
  },
  minInterval: 100,
  axisLabel: {
    formatter: '{value}\nUSD'
  }
}

const defaultDataZoom = function (startValue) {
  if (startValue) {
    return [{
      type: 'slider',
      height: 15,
      bottom: '5px',
      startValue: startValue,
      end: 100
    }]
  } else {
    return [{
      type: 'slider',
      height: 15,
      bottom: '5px',
      start: 0,
      end: 100
    }]
  }
}

const defaultToolbox = function (headers, data, title) {
  return {
    feature: {
      dataView: {
        title: '',
        optionToContent: () => jsonToTable(headers, data, title)
      },
      myTool: {
        show: true,
        title: 'Downlaod Data',
        icon: 'path://M4.7,22.9L29.3,45.5L54.7,23.4M4.6,43.6L4.6,58L53.8,58L53.8,43.6M29.2,45.1L29.2,0',
        onclick: () => {
          const timeStamp = Date.now()
          const formattedString = date.formatDate(timeStamp, 'YYYY-MM-DD')
          jsonToExcel(headers, data, title + formattedString)
        }
      }
    }
  }
}

const defaultLegend = { left: 10, top: 20 }

const defaultEchartOption = {
  textStyle: {
    fontSize: 10
  },
  title: {
    text: 'This is title',
    subtext: 'This is subtitle',
    left: 'center'
  },
  legend: defaultLegend,
  grid: [{}],
  tooltip: defaultTooltip
}

const defaultLineSerial = function (index, value, labelFormatter, dimensions, encodeX, encodeY) {
  return {
    type: 'line',
    datasetIndex: index,
    name: value,
    label: {
      show: true,
      position: 'bottom',
      formatter: labelFormatter
    },
    tooltip: {
      trigger: 'item',
      formatter: (params, ticket, callback) => jsonToMultLine(dimensions, params.data)
    },
    dimensions: dimensions,
    encode: {
      x: encodeX,
      y: encodeY
    }
  }
}
const defaultBarSerial = function (index, value, labelFormatter, dimensions, encodeX, encodeY) {
  return {
    type: 'bar',
    datasetIndex: index,
    name: value,
    label: {
      show: true,
      position: 'inside',
      formatter: labelFormatter
    },
    tooltip: {
      trigger: 'item',
      formatter: (params, ticket, callback) => jsonToMultLine(dimensions, params.data)
    },
    dimensions: dimensions,
    encode: {
      x: encodeX,
      y: encodeY
    }
  }
}

const defaultBarStackedSerial = function (index, value, labelFormatter, dimensions, encodeX, encodeY) {
  return mergerOption(defaultBarSerial(index, value, labelFormatter, dimensions, encodeX, encodeY), { stack: 'total' })
}

const DefaultPieSerial = function (index, labelFormatter, dimensions, summed, encode) {
  return {
    type: 'pie',
    datasetIndex: index,
    radius: [0, '30%'],
    label: {
      formatter: labelFormatter
    },
    tooltip: {
      trigger: 'item',
      formatter: (params, ticket, callback) =>
        _get(params.data, summed) + '<br/>' + encode + ':' + _get(params.data, encode) + '<br/>'
    },
    labelLine: {
    },
    dimensions: dimensions,
    encode: {
      value: encode
    }
  }
}

const AttachedPieSerial = function (index, labelFormatter, dimensions, summed, encode) {
  return mergerOption(DefaultPieSerial(index, labelFormatter, dimensions, summed, encode), { center: ['88%', '50%'] })
}

function mergerOption (defaultOption, clientOption) {
  const defaultOptionClone = _cloneDeep(defaultOption)
  return _merge(defaultOptionClone, clientOption)
}

export {
  echarts,
  defaultSeriesColor,
  defaultTooltip,
  defaultEchartOption,
  defaultXAxisTime,
  defaultYAxisUSD,
  defaultLegend,
  defaultDataZoom,
  defaultToolbox,
  defaultLineSerial,
  AttachedPieSerial,
  defaultBarSerial,
  defaultBarStackedSerial,
  mergerOption,
  jsonToMultLine
}
