/***
 * @Author         : Robert Huang<56649783@qq.com>
 * @Date           : 2022-03-25 11:01:23
 * @LastEditors    : Robert Huang<56649783@qq.com>
 * @LastEditTime   : 2022-05-28 23:15:01
 * @FilePath       : \web2\src\mock\services\products.js
 * @CopyRight      : Dedienne Aerospace China ZhuHai
 */
import { getQueryParameters } from '@/assets/mockExt'
import { orderBy } from 'lodash'
import Mock from 'mockjs'

// Mock.setup({ timeout: '500-1000' })
Mock.mock(RegExp('^(/Data/PNHelper)' + '.*'), (options) => {
  console.debug('\u001b[35m' + '[Mocking] ', 'PNHelper')

  const PnOrPnRoot = getQueryParameters(options)['PN']
  const PnRoot = PnOrPnRoot.split('_')[0]
  const PnVer = PnOrPnRoot.split('_')[1]

  if (PnVer) {
    return [{ PNROOT: PnRoot, PN: PnRoot + '_' + PnVer }]
  }
  // list = {data:[{},{}]}
  const list = Mock.mock({
    'data|1-10': [
      {
        PNROOT: PnRoot,
        PN: () => {
          return PnRoot + '_' + Mock.mock('@character("ABCDEFG")')
        }
      }
    ]
  })

  return list.data
  // return [{ PNROOT: '123', PN: '123_A' }, { PNROOT: '456', PN: '456_B' }, { PNROOT: '789', PN: '789_C' }]
})

Mock.mock(RegExp('^(/Data/PNsInFamily)' + '.*'), (options) => {
  console.debug('\u001b[35m' + '[Mocking] ', 'PNsInFamily')

  const PnRoot = getQueryParameters(options)['PnRoot']

  // list = {data:[{},{}]}
  const list = Mock.mock({
    'data|1-10': [
      {
        'ROWID|+1': 1,
        PNROOT: PnRoot,
        PN: () => {
          return PnRoot + '_' + Mock.mock('@character("ABCDEFG")')
        },
        Cat: /(P|C|S)000[1-5]/,
        Version: /[A-Z]/,
        Comment: /[A-Z][A-Z]/,
        Desc1: () => {
          return Mock.mock('@sentence(3, 5)')
        },
        Desc2: () => {
          return Mock.mock('@sentence(3, 5)')
        },
        Desc3: () => {
          return Mock.mock('@sentence(3, 5)')
        },
        'Status|1-2': 1,
        CreateDate: () => {
          return Mock.mock('@datetime("yyyy-MM-dd")')
        }
      }
    ]
  })

  return list.data
})

Mock.mock(RegExp('^(/Data/QuoteHistory)' + '.*'), (options) => {
  console.debug('\u001b[35m' + '[Mocking] ', 'QuoteHistory')

  const PnRoot = getQueryParameters(options)['PnRoot']

  // list = {data:[{},{}]}
  const list = Mock.mock({
    'data|1-10': [
      {
        Currency: /(RMB|USD|EUR|HKD)/,
        CustomerCode: /\d{5}/,
        CustomerName: () => {
          return Mock.mock('@title(3, 5)')
        },
        'NetPrice|1000-2000': 1,
        OrderFlag: /\d[01]/,
        OrderNO: /((H|Z|S|T|M)CC[0-9]{6})?/,
        PN: () => {
          return PnRoot + '_' + Mock.mock('@character("ABCDEFG")')
        },
        'Qty|1-200': 1,
        QuoteDate: () => {
          return Mock.mock('@date("yyyy-MM-dd")')
        },
        QuoteNO: /(H|Z|S|T|M)DC[0-9]{6}/,
        'USD|1000-2000': 1,
        'Rate|6-9': 1,
        TradeTerm: /(DDU|CFR|FOB|DAF|CIP|EXW|DAP)/,
        SalesSite: /(ZHU|HKG|SGP|TLS|MIA)/
      }
    ]
  })
  return orderBy(list.data, ['OrderDate'], ['asc'])
})

Mock.mock(RegExp('^(/Data/SalesHistory)' + '.*'), (options) => {
  console.debug('\u001b[35m' + '[Mocking] ', 'SalesHistory')

  const PnRoot = getQueryParameters(options)['PnRoot']

  // list = {data:[{},{}]}
  const list = Mock.mock({
    'data|1-100': [
      {
        Currency: /(RMB|USD|EUR|HKD)/,
        CustomerCode: /\d{5}/,
        CustomerName: () => {
          return Mock.mock('@title(3, 5)')
        },
        OrderDate: () => {
          return Mock.mock('@date("yyyy-MM-dd")')
        },
        OrderNO: /(H|Z|S|T|M)CC[0-9]{6}/,
        'NetPrice|1000-2000': 1,
        PN: () => {
          return PnRoot + '_' + Mock.mock('@character("ABCDEFG")')
        },
        'Qty|1-100': 1,
        'Rate|6-9': 1,
        'USD|1000-2000': 1,
        TradeTerm: /(DDU|CFR|FOB|DAF|CIP|EXW|DAP)/,
        SalesSite: /(ZHU|HKG|SGP|TLS|MIA)/
      }
    ]
  })
  return orderBy(list.data, ['OrderDate'], ['asc'])
})

Mock.mock(RegExp('^(/Data/CostHistory)' + '.*'), (options) => {
  console.debug('\u001b[35m' + '[Mocking] ', 'CostHistory')

  const PnRoot = getQueryParameters(options)['PnRoot']

  // list = {data:[{},{}]}
  const list = Mock.mock({
    'data|1-10': [
      {
        Currency: /(RMB|USD|EUR|HKD)/,
        VendorCode: /\d{5}/,
        VendorName: () => {
          return Mock.mock('@title(3, 5)')
        },
        OrderDate: () => {
          return Mock.mock('@date("yyyy-MM-dd")')
        },
        ProjectNO: /(H|Z|S|T|M)CC[0-9]{6}-[1-9]{1}/,
        'NetPrice|1000-2000': 1,
        OrderPN: () => {
          return PnRoot + '_' + Mock.mock('@character("ABCDEFG")')
        },
        PurchasePN: () => {
          return PnRoot + '_' + Mock.mock('@character("ABCDEFG")')
        },
        PurchaseNO: /(H|Z|S|T|M)CF[0-9]{6}/,
        Description: () => {
          return Mock.mock('@title(3, 5)')
        },
        Line: /[0-9]{1}/,
        'Qty|1-100': 1,
        'Rate|6-9': 1,
        'USD|1000-2000': 1,
        PurchaseSite: /(ZHU|HKG|SGP|TLS|MIA)/
      }
    ]
  })
  return orderBy(list.data, ['OrderDate'], ['asc'])
})

Mock.mock(RegExp('^(/Data/InventoryStock)' + '.*'), (options) => {
  console.debug('\u001b[35m' + '[Mocking] ', 'InventoryStock')

  const PnRoot = getQueryParameters(options)['PnRoot']

  // list = {data:[{},{}]}
  const list = Mock.mock({
    'data|1-10': [
      {
        StockSite: /(ZHU|HKG|SGP|TLS|MIA)/,
        PN: () => {
          return PnRoot + '_' + Mock.mock('@character("ABCDEFG")')
        },
        'Qty|1-365': 1
      }
    ]
  })

  return list.data
})

Mock.mock(RegExp('^(/Data/DeliveryDuration)' + '.*'), (options) => {
  console.debug('\u001b[35m' + '[Mocking] ', 'DeliveryDuration')

  const PnRoot = getQueryParameters(options)['PnRoot']

  // list = {data:[{},{}]}
  const list = Mock.mock({
    'data|1-10': [
      {
        SalesSite: /(ZHU|HKG|SGP|TLS|MIA)/,
        PN: () => {
          return PnRoot + '_' + Mock.mock('@character("ABCDEFG")')
        },
        OrderDate: () => {
          return Mock.mock('@date("yyyy-MM-dd")')
        },
        ShipDate: () => {
          return Mock.mock('@date("yyyy-MM-dd")')
        },
        'Duration|1-365': 1
      }
    ]
  })

  return list.data
})
