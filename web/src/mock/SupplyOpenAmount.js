import Mock from 'mockjs'
import { getUrlParam } from '../js/getUrlParam'

Mock.setup({
  timeout: '1000-3000'
})

Mock.mock(RegExp('^(/Data/SupplyOpenAmount)' + '.*'), options => {
  const SupplyCode = getUrlParam(options.url, 'SupplyCode')

  // list = {data:[{},{}]}
  const list = Mock.mock({
    'data|1-5': [
      {
        Site: /ZHU/,
        SupplyCode: SupplyCode,
        SupplyName: () => { return Mock.mock('@title(3, 5)') },
        Currency: /(RMB|USD|EUR|HKD)/,
        'Amount|1000-2000': 1,
        'USD|1000-20000': 1,
        'Rate|6-9': 1
      }, {
        Site: /HKG/,
        SupplyCode: SupplyCode,
        SupplyName: () => { return Mock.mock('@title(3, 5)') },
        Currency: /(RMB|USD|EUR|HKD)/,
        'Amount|1000-2000': 1,
        'USD|1000-20000': 1,
        'Rate|6-9': 1
      }, {
        Site: /TLS/,
        SupplyCode: SupplyCode,
        SupplyName: () => { return Mock.mock('@title(3, 5)') },
        Currency: /(RMB|USD|EUR|HKD)/,
        'Amount|1000-2000': 1,
        'USD|1000-20000': 1,
        'Rate|6-9': 1
      }, {
        Site: /SGP/,
        SupplyCode: SupplyCode,
        SupplyName: () => { return Mock.mock('@title(3, 5)') },
        Currency: /(RMB|USD|EUR|HKD)/,
        'Amount|1000-2000': 1,
        'USD|1000-20000': 1,
        'Rate|6-9': 1
      }, {
        Site: /MIA/,
        SupplyCode: SupplyCode,
        SupplyName: () => { return Mock.mock('@title(3, 5)') },
        Currency: /(RMB|USD|EUR|HKD)/,
        'Amount|1000-2000': 1,
        'USD|1000-20000': 1,
        'Rate|6-9': 1
      }
    ]
  })

  return list.data
})
