import Mock from 'mockjs'
import { getUrlParam } from '../js/getUrlParam'

Mock.setup({
  timeout: '1000-3000'
})

Mock.mock(RegExp('^(/Data/DeliveryDuration)' + '.*'), options => {
  const PnRoot = getUrlParam(options.url, 'PnRoot')

  // list = {data:[{},{}]}
  const list = Mock.mock({
    'data|1-10': [
      {
        'ROWID|+1': 1,
        PN: () => { return PnRoot + '_' + Mock.mock('@character("ABCDEFG")') },
        SalesSite: /(ZHU|HKG|SGP|TLS|MIA)/,
        'Min|1-365': 1,
        'Avg|1-365': 1,
        'Max|1-365': 1
      }
    ]
  })

  return list.data
})
