import Mock from 'mockjs'
import { getUrlParam } from '../js/getUrlParam'

Mock.setup({
  timeout: '1000-3000'
})

Mock.mock(RegExp('^(/Data/QuoteHistory)' + '.*'), options => {
  let PnRoot = getUrlParam(options.url, 'PnRoot')

  // list = {data:[{},{}]}
  let list = Mock.mock({
    'data|1-10': [
      {
        Currency: /(RMB|USD|EUR|HKD)/,
        CustomerCode: /\d{5}/,
        CustomerName: () => { return Mock.mock('@title(3, 5)') },
        'NetPrice|1000-2000': 1,
        OrderFlag: /\d[01]/,
        OrderNO: /((H|Z|S|T|M)CC[0-9]{6})?/,
        PN: () => { return PnRoot + '_' + Mock.mock('@character("ABCDEFG")') },
        'Qty|1-200': 1,
        QuoteDate: () => { return Mock.mock('@date("yyyy-MM-dd")') },
        QuoteNO: /(H|Z|S|T|M)DC[0-9]{6}/,
        'RMB|1000-2000': 1,
        'Rate|6-9': 1,
        SalesSite: /(ZHU|HKG|SGP|TLS|MIA)/
      }
    ]
  })
  return list.data
})
