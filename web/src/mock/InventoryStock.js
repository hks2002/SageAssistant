import Mock from 'mockjs'
import { getUrlParam } from '../js/getUrlParam'

Mock.setup({
  timeout: '1000-3000'
})

Mock.mock(RegExp('^(/Data/InventoryStock)' + '.*'), options => {
  let PnRoot = getUrlParam(options.url, 'PnRoot')

  // list = {data:[{},{}]}
  let list = Mock.mock({
    'data|1-10': [
      {
        'ROWID|+1': 1,
        PN: () => { return PnRoot + '_' + Mock.mock('@character("ABCDEFG")') },
        StockSite: /(ZHU|HKG|SGP|TLS|MIA)/,
        'Qty|1-10': 1
      }
    ]
  })

  return list.data
})
