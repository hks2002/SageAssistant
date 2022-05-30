/***
 * @Author         : Robert Huang<56649783@qq.com>
 * @Date           : 2022-03-31 17:10:21
 * @LastEditors    : Robert Huang<56649783@qq.com>
 * @LastEditTime   : 2022-05-28 23:16:46
 * @FilePath       : \web2\src\mock\services\customers.js
 * @CopyRight      : Dedienne Aerospace China ZhuHai
 */
import { getQueryParameters } from '@/assets/mockExt'
import { orderBy } from 'lodash'
import Mock from 'mockjs'

Mock.mock(RegExp('^(/Data/CustomerHelper)' + '.*'), (options) => {
  console.debug('\u001b[35m' + '[Mocking] ', 'CustomerHelper')

  const query = getQueryParameters(options)
  const CodeOrName = query['CustomerName']

  if (CodeOrName === '%%') {
    return [
      {
        CustomerCode: '%%',
        CustomerName: '%%'
      }
    ]
  }
  // list = {data:[{},{}]}
  const list = Mock.mock({
    'data|1-10': [
      {
        CustomerCode: CodeOrName,
        CustomerName: () => {
          return CodeOrName + '_' + Mock.mock('@title(3, 5)')
        }
      }
    ]
  })

  return list.data
})

Mock.mock(RegExp('^(/Data/CustomerDetails)' + '.*'), (options) => {
  console.debug('\u001b[35m' + '[Mocking] ', 'CustomerDetails')

  const query = getQueryParameters(options)
  const CustomerCode = query['CustomerCode']
  // list = {data:[{},{}]}
  const list = Mock.mock({
    'data|1-2': [
      {
        CustomerCode: CustomerCode,
        CustomerName0: () => {
          return Mock.mock('@title(3, 5)')
        },
        CustomerName1: () => {
          return Mock.mock('@title(3, 5)')
        },
        PostCode: /[0-9]{6}/,
        Contry: /[A-Z]{3}/,
        State: /[A-Z]{1}[a-z]{2,6}/,
        City: /[A-Z]{1}[a-z]{2,6}/,
        Address0: () => {
          return Mock.mock('@title(3, 5)')
        },
        Address1: () => {
          return Mock.mock('@title(3, 5)')
        },
        Tel0: /\d{3}-\d{8}|\d{4}-\d{7}/,
        Tel1: /\d{3}-\d{8}|\d{4}-\d{7}/,
        Tel2: /\d{3}-\d{8}|\d{4}-\d{7}/,
        Tel3: /\d{3}-\d{8}|\d{4}-\d{7}/,
        Tel4: /\d{3}-\d{8}|\d{4}-\d{7}/,
        Mobile0:
          /^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\d{8}$/,
        Fax0: /\d{3}-\d{8}|\d{4}-\d{7}/,
        Email0: /^\w{2,6}@\w{2,6}\.\w{2,6}$/,
        Email1: /^\w{2,6}@\w{2,6}\.\w{2,6}$/,
        Email2: /^\w{2,6}@\w{2,6}\.\w{2,6}$/,
        Email3: /^\w{2,6}@\w{2,6}\.\w{2,6}$/,
        Email4: /^\w{2,6}@\w{2,6}\.\w{2,6}$/,
        Website: /^\w+([-+.]\w+)@\w+\.\w+([-.]\w+)$/
      }
    ]
  })

  return list.data
})

Mock.mock(RegExp('^(/Data/CustomerDeliveryHistory)' + '.*'), (options) => {
  console.debug('\u001b[35m' + '[Mocking] ', 'CustomerDeliveryHistory')

  const query = getQueryParameters(options)
  const CustomerCode = query['CustomerCode']

  // list = {data:[{},{}]}
  const list = Mock.mock({
    'data|1-500': [
      {
        Site: /ZHU|HKG|TLS|SGP|MIA|CIN|QAT/,
        CustomerCode: CustomerCode,
        OrderNO: /(H|Z|S|T|M)CC[0-9]{6}/,
        ProjectNO: /(H|Z|S|T|M)CC[0-9]{6}-\d/,
        PN: /[A-Z]{5,8}/,
        Description: () => {
          return Mock.mock('@title(3, 5)')
        },
        ShipDate: () => {
          return Mock.mock('@date("yyyy-MM-dd")')
        },
        DemandDate: () => {
          return Mock.mock('@date("yyyy-MM-dd")')
        },
        OrderDate: () => {
          return Mock.mock('@date("yyyy-MM-dd")')
        },
        DeliveryNO: /(H|Z|S|T|M)BL[0-9]{6}/,
        DaysNeed: () => {
          return Mock.mock('@integer(20, 365)')
        }
      }
    ]
  })
  return orderBy(list.data, ['ReceiptDate'], ['asc'])
})

Mock.mock(RegExp('^(/Data/CustomerDelayHistory)' + '.*'), (options) => {
  console.debug('\u001b[35m' + '[Mocking] ', 'CustomerDelayHistory')

  const query = getQueryParameters(options)
  const CustomerCode = query['CustomerCode']

  // list = {data:[{},{}]}
  const list = Mock.mock({
    'data|1-500': [
      {
        Site: /ZHU|HKG|TLS|SGP|MIA|CIN|QAT/,
        CustomerCode: CustomerCode,
        OrderNO: /(H|Z|S|T|M)CC[0-9]{6}/,
        ProjectNO: /(H|Z|S|T|M)CC[0-9]{6}-\d/,
        PN: /[A-Z]{5,8}/,
        Description: () => {
          return Mock.mock('@title(3, 5)')
        },
        ShipDate: () => {
          return Mock.mock('@date("yyyy-MM-dd")')
        },
        DemandDate: () => {
          return Mock.mock('@date("yyyy-MM-dd")')
        },
        OrderDate: () => {
          return Mock.mock('@date("yyyy-MM-dd")')
        },
        DeliveryNO: /(H|Z|S|T|M)BL[0-9]{6}/,
        DaysDelay: () => {
          return Mock.mock('@integer(20, 365)')
        }
      }
    ]
  })

  return list.data
})

Mock.mock(RegExp('^(/Data/CustomerOpenAmount)' + '.*'), (options) => {
  console.debug('\u001b[35m' + '[Mocking] ', 'CustomerOpenAmount')

  const query = getQueryParameters(options)
  const CustomerCode = query['CustomerCode']

  // list = {data:[{},{}]}
  const list = Mock.mock({
    'data|1-5': [
      {
        Site: /ZHU/,
        CustomerCode: CustomerCode,
        Currency: /(RMB|USD|EUR|HKD)/,
        'Amount|1000-2000': 1,
        'USD|1000-20000': 1,
        'Rate|6-9': 1
      },
      {
        Site: /HKG/,
        CustomerCode: CustomerCode,
        Currency: /(RMB|USD|EUR|HKD)/,
        'Amount|1000-2000': 1,
        'USD|1000-20000': 1,
        'Rate|6-9': 1
      },
      {
        Site: /TLS/,
        CustomerCode: CustomerCode,
        Currency: /(RMB|USD|EUR|HKD)/,
        'Amount|1000-2000': 1,
        'USD|1000-20000': 1,
        'Rate|6-9': 1
      },
      {
        Site: /SGP/,
        CustomerCode: CustomerCode,
        Currency: /(RMB|USD|EUR|HKD)/,
        'Amount|1000-2000': 1,
        'USD|1000-20000': 1,
        'Rate|6-9': 1
      },
      {
        Site: /MIA/,
        CustomerCode: CustomerCode,
        Currency: /(RMB|USD|EUR|HKD)/,
        'Amount|1000-2000': 1,
        'USD|1000-20000': 1,
        'Rate|6-9': 1
      }
    ]
  })

  return list.data
})

Mock.mock(RegExp('^(/Data/CustomerOpenItems)' + '.*'), (options) => {
  console.debug('\u001b[35m' + '[Mocking] ', 'CustomerOpenItems')

  const query = getQueryParameters(options)
  const CustomerCode = query['CustomerCode']
  // list = {data:[{},{}]}
  const list = Mock.mock({
    'data|1-50': [
      {
        Site: /ZHU|HKG|TLS|SGP|MIA|CIN|QAT/,
        CustomerCode: CustomerCode,
        OrderNO: /(H|Z|S|T|M)CF[0-9]{6}/,
        ProjectNO: /(H|Z|S|T|M)CC[0-9]{6}-\d/,
        PN: /[A-Z]{5,8}/,
        Description: () => {
          return Mock.mock('@title(3, 5)')
        },
        ShipDate: () => {
          return Mock.mock('@date("yyyy-MM-dd")')
        },
        DemandDate: () => {
          return Mock.mock('@date("yyyy-MM-dd")')
        },
        OrderDate: () => {
          return Mock.mock('@date("yyyy-MM-dd")')
        },
        DaysDelay: () => {
          return Mock.mock('@integer(20, 365)')
        }
      }
    ]
  })

  return list.data
})

Mock.mock(RegExp('^(/Data/CustomerOpenQty)' + '.*'), (options) => {
  console.debug('\u001b[35m' + '[Mocking] ', 'CustomerOpenQty')

  const query = getQueryParameters(options)
  const CustomerCode = query['CustomerCode']
  // list = {data:[{},{}]}
  const list = Mock.mock({
    'data|1-5': [
      {
        Site: /ZHU/,
        CustomerCode: CustomerCode,
        CountType: /Item|Product|Project/,
        Qty: () => {
          return Mock.mock('@integer(20, 10000)')
        }
      },
      {
        Site: /HKG/,
        CustomerCode: CustomerCode,
        CountType: /Item|Product|Project/,
        Qty: () => {
          return Mock.mock('@integer(20, 10000)')
        }
      },
      {
        Site: /TLS/,
        CustomerCode: CustomerCode,
        CountType: /Item|Product|Project/,
        Qty: () => {
          return Mock.mock('@integer(20, 10000)')
        }
      },
      {
        Site: /SGP/,
        CustomerCode: CustomerCode,
        CountType: /Item|Product|Project/,
        Qty: () => {
          return Mock.mock('@integer(20, 10000)')
        }
      },
      {
        Site: /MIA/,
        CustomerCode: CustomerCode,
        CountType: /Item|Product|Project/,
        Qty: () => {
          return Mock.mock('@integer(20, 10000)')
        }
      },
      {
        Site: /QAT/,
        CustomerCode: CustomerCode,
        CountType: /Item|Product|Project/,
        Qty: () => {
          return Mock.mock('@integer(20, 10000)')
        }
      },
      {
        Site: /CIN/,
        CustomerCode: CustomerCode,
        CountType: /Item|Product|Project/,
        Qty: () => {
          return Mock.mock('@integer(20, 10000)')
        }
      }
    ]
  })

  return list.data
})

Mock.mock(RegExp('^(/Data/CustomerTotalAmount)' + '.*'), (options) => {
  console.debug('\u001b[35m' + '[Mocking] ', 'CustomerTotalAmount')

  const query = getQueryParameters(options)
  const CustomerCode = query['CustomerCode']
  // list = {data:[{},{}]}
  const list = Mock.mock({
    'data|1-5': [
      {
        Site: /ZHU/,
        CustomerCode: CustomerCode,
        Currency: /(RMB|USD|EUR|HKD)/,
        'Amount|1000-2000': 1,
        'USD|1000-20000': 1,
        'Rate|6-9': 1
      },
      {
        Site: /HKG/,
        CustomerCode: CustomerCode,
        Currency: /(RMB|USD|EUR|HKD)/,
        'Amount|1000-2000': 1,
        'USD|1000-20000': 1,
        'Rate|6-9': 1
      },
      {
        Site: /TLS/,
        CustomerCode: CustomerCode,
        Currency: /(RMB|USD|EUR|HKD)/,
        'Amount|1000-2000': 1,
        'USD|1000-20000': 1,
        'Rate|6-9': 1
      },
      {
        Site: /SGP/,
        CustomerCode: CustomerCode,
        Currency: /(RMB|USD|EUR|HKD)/,
        'Amount|1000-2000': 1,
        'USD|1000-20000': 1,
        'Rate|6-9': 1
      },
      {
        Site: /MIA/,
        CustomerCode: CustomerCode,
        Currency: /(RMB|USD|EUR|HKD)/,
        'Amount|1000-2000': 1,
        'USD|1000-20000': 1,
        'Rate|6-9': 1
      }
    ]
  })

  return list.data
})

Mock.mock(RegExp('^(/Data/CustomerTotalQty)' + '.*'), (options) => {
  console.debug('\u001b[35m' + '[Mocking] ', 'CustomerTotalQty')

  const query = getQueryParameters(options)
  const CustomerCode = query['CustomerCode']
  // list = {data:[{},{}]}
  const list = Mock.mock({
    'data|1-5': [
      {
        Site: /ZHU/,
        CustomerCode: CustomerCode,
        CountType: /Item|Product|Project/,
        Qty: () => {
          return Mock.mock('@integer(20, 1000)')
        }
      },
      {
        Site: /HKG/,
        CustomerCode: CustomerCode,
        CountType: /Item|Product|Project/,
        Qty: () => {
          return Mock.mock('@integer(20, 1000)')
        }
      },
      {
        Site: /TLS/,
        CustomerCode: CustomerCode,
        CountType: /Item|Product|Project/,
        Qty: () => {
          return Mock.mock('@integer(20, 1000)')
        }
      },
      {
        Site: /SGP/,
        CustomerCode: CustomerCode,
        CountType: /Item|Product|Project/,
        Qty: () => {
          return Mock.mock('@integer(20, 1000)')
        }
      },
      {
        Site: /MIA/,
        CustomerCode: CustomerCode,
        CountType: /Item|Product|Project/,
        Qty: () => {
          return Mock.mock('@integer(20, 1000)')
        }
      },
      {
        Site: /QAT/,
        CustomerCode: CustomerCode,
        CountType: /Item|Product|Project/,
        Qty: () => {
          return Mock.mock('@integer(20, 1000)')
        }
      },
      {
        Site: /CIN/,
        CustomerCode: CustomerCode,
        CountType: /Item|Product|Project/,
        Qty: () => {
          return Mock.mock('@integer(20, 1000)')
        }
      }
    ]
  })

  return list.data
})
