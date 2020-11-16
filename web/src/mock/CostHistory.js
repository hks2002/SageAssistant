import Mock from 'mockjs'
import { getUrlParam } from '../js/getUrlParam'
const _orderBy = require('lodash/orderBy')

Mock.setup({
  timeout: '1000-3000'
})

Mock.mock(RegExp('^(/Data/CostHistory)' + '.*'), options => {
  const PnRoot = getUrlParam(options.url, 'PnRoot')

  // list = {data:[{},{}]}
  const list = Mock.mock({
    'data|1-10': [
      {
        Currency: /(RMB|USD|EUR|HKD)/,
        VendorCode: /\d{5}/,
        VendorName: () => { return Mock.mock('@title(3, 5)') },
        OrderDate: () => { return Mock.mock('@date("yyyy-MM-dd")') },
        ProjectNO: /(H|Z|S|T|M)CC[0-9]{6}-[1-9]{1}/,
        'NetPrice|1000-2000': 1,
        OrderPN: () => { return PnRoot + '_' + Mock.mock('@character("ABCDEFG")') },
        PurchasePN: () => { return PnRoot + '_' + Mock.mock('@character("ABCDEFG")') },
        PurchaseNO: /(H|Z|S|T|M)CF[0-9]{6}/,
        Description: () => { return Mock.mock('@title(3, 5)') },
        Line: /[0-9]{1}/,
        'Qty|1-100': 1,
        'Rate|6-9': 1,
        'USD|1000-2000': 1,
        PurchaseSite: /(ZHU|HKG|SGP|TLS|MIA)/
      }
    ]
  })
  return _orderBy(list.data, ['OrderDate'], ['asc'])
})
