import Mock from 'mockjs'
import { getUrlParam } from '../js/getUrlParam'
let _orderBy = require('lodash/orderBy')

Mock.setup({
  timeout: '1000-3000'
})

Mock.mock(RegExp('^(/Data/SalesHistory)' + '.*'), options => {
  let PnRoot = getUrlParam(options.url, 'PnRoot')

  // list = {data:[{},{}]}
  let list = Mock.mock({
    'data|1-100': [
      {
        Currency: /(RMB|USD|EUR|HKD)/,
        CustomerCode: /\d{5}/,
        CustomerName: () => { return Mock.mock('@title(3, 5)') },
        OrderDate: () => { return Mock.mock('@date("yyyy-MM-dd")') },
        OrderNO: /(H|Z|S|T|M)CC[0-9]{6}/,
        'NetPrice|1000-2000': 1,
        PN: () => { return PnRoot + '_' + Mock.mock('@character("ABCDEFG")') },
        'Qty|1-100': 1,
        'Rate|6-9': 1,
        'USD|1000-2000': 1,
        SalesSite: /(ZHU|HKG|SGP|TLS|MIA)/
      }
    ]
  })
  return _orderBy(list.data, ['OrderDate'], ['asc'])
})
