import Mock from 'mockjs'
import { getUrlParam } from '../js/getUrlParam'

Mock.setup({
  timeout: '1000-3000'
})

Mock.mock(RegExp('^(/Data/SupplyTotalQty)' + '.*'), options => {
  const SupplyCode = getUrlParam(options.url, 'SupplyCode')

  // list = {data:[{},{}]}
  const list = Mock.mock({
    'data|1-5': [
      {
        Site: /ZHU/,
        SupplyCode: SupplyCode,
        CountType: /Item|Product|Project/,
        Qty: () => { return Mock.mock('@integer(20, 1000)') }
      },
      {
        Site: /HKG/,
        SupplyCode: SupplyCode,
        CountType: /Item|Product|Project/,
        Qty: () => { return Mock.mock('@integer(20, 1000)') }
      },
      {
        Site: /TLS/,
        SupplyCode: SupplyCode,
        CountType: /Item|Product|Project/,
        Qty: () => { return Mock.mock('@integer(20, 1000)') }
      },
      {
        Site: /SGP/,
        SupplyCode: SupplyCode,
        CountType: /Item|Product|Project/,
        Qty: () => { return Mock.mock('@integer(20, 1000)') }
      },
      {
        Site: /MIA/,
        SupplyCode: SupplyCode,
        CountType: /Item|Product|Project/,
        Qty: () => { return Mock.mock('@integer(20, 1000)') }
      },
      {
        Site: /QAT/,
        SupplyCode: SupplyCode,
        CountType: /Item|Product|Project/,
        Qty: () => { return Mock.mock('@integer(20, 1000)') }
      },
      {
        Site: /CIN/,
        SupplyCode: SupplyCode,
        CountType: /Item|Product|Project/,
        Qty: () => { return Mock.mock('@integer(20, 1000)') }
      }
    ]
  })

  return list.data
})
