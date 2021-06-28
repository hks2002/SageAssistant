import Mock from 'mockjs'
import { getUrlParam } from '../js/getUrlParam'
const _orderBy = require('lodash/orderBy')

Mock.setup({
  timeout: '1000-3000'
})

Mock.mock(RegExp('^(/Data/SupplyDeliveryHistory)' + '.*'), options => {
  const SupplyCode = getUrlParam(options.url, 'SupplyCode')

  // list = {data:[{},{}]}
  const list = Mock.mock({
    'data|1-500': [
      {
        Site: /ZHU|HKG|TLS|SGP|MIA|CIN|QAT/,
        SupplyCode: SupplyCode,
        PurchaseNO: /(H|Z|S|T|M)CF[0-9]{6}/,
        ProjectNO: /(H|Z|S|T|M)CC[0-9]{6}-\d/,
        PN: /[A-Z]{5,8}/,
        Description: () => { return Mock.mock('@title(3, 5)') },
        AckDate: () => { return Mock.mock('@date("yyyy-MM-dd")') },
        ExpectDate: () => { return Mock.mock('@date("yyyy-MM-dd")') },
        OrderDate: () => { return Mock.mock('@date("yyyy-MM-dd")') },
        ReceiptNO: /(H|Z|S|T|M)RA[0-9]{6}/,
        ReceiptDate: () => { return Mock.mock('@date("yyyy-MM-dd")') },
        DaysNeed: () => { return Mock.mock('@integer(20, 365)') }
      }
    ]
  })
  return _orderBy(list.data, ['ReceiptDate'], ['asc'])
})
