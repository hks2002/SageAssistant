import Mock from 'mockjs'
import { orderBy } from 'lodash'
import { getQueryParameter } from '../mockExt'

Mock.mock(RegExp('^(/Data/SupplierHelper)' + '.*'), options => {
  console.debug('\u001b[35m' + '[Mocking] ', 'SupplierHelper')

  const CodeOrName = getQueryParameter(options, 'SupplierCodeOrName')

  // list = {data:[{},{}]}
  const list = Mock.mock({
    'data|1-10': [
      {
        SupplierCode: CodeOrName,
        SupplierName: () => { return CodeOrName + '_' + Mock.mock('@title(3, 5)') }
      }
    ]
  })

  return list.data
})

Mock.mock(RegExp('^(/Data/SupplierDetails)' + '.*'), options => {
  console.debug('\u001b[35m' + '[Mocking] ', 'SupplierDetails')

  const SupplierCode = getQueryParameter(options, 'SupplierCode')
  // list = {data:[{},{}]}
  const list = Mock.mock({
    'data|1-2': [
      {
        SupplierCode: SupplierCode,
        SupplierName0: () => { return Mock.mock('@title(3, 5)') },
        SupplierName1: () => { return Mock.mock('@title(3, 5)') },
        PostCode: /[0-9]{6}/,
        Contry: /[A-Z]{3}/,
        State: /[A-Z]{1}[a-z]{2,6}/,
        City: /[A-Z]{1}[a-z]{2,6}/,
        Address0: () => { return Mock.mock('@title(3, 5)') },
        Address1: () => { return Mock.mock('@title(3, 5)') },
        Tel0: /\d{3}-\d{8}|\d{4}-\d{7}/,
        Tel1: /\d{3}-\d{8}|\d{4}-\d{7}/,
        Tel2: /\d{3}-\d{8}|\d{4}-\d{7}/,
        Tel3: /\d{3}-\d{8}|\d{4}-\d{7}/,
        Tel4: /\d{3}-\d{8}|\d{4}-\d{7}/,
        Mobile0: /^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\d{8}$/,
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

Mock.mock(RegExp('^(/Data/SupplierDeliveryHistory)' + '.*'), options => {
  console.debug('\u001b[35m' + '[Mocking] ', 'SupplierDeliveryHistory')

  const SupplierCode = getQueryParameter(options, 'SupplierCode')

  // list = {data:[{},{}]}
  const list = Mock.mock({
    'data|1-500': [
      {
        Site: /ZHU|HKG|TLS|SGP|MIA|CIN|QAT/,
        SupplierCode: SupplierCode,
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
  return orderBy(list.data, ['ReceiptDate'], ['asc'])
})

Mock.mock(RegExp('^(/Data/SupplierDelayHistory)' + '.*'), options => {
  console.debug('\u001b[35m' + '[Mocking] ', 'SupplierDelayHistory')

  const SupplierCode = getQueryParameter(options, 'SupplierCode')

  // list = {data:[{},{}]}
  const list = Mock.mock({
    'data|1-500': [
      {
        Site: /ZHU|HKG|TLS|SGP|MIA|CIN|QAT/,
        SupplierCode: SupplierCode,
        PurchaseNO: /(H|Z|S|T|M)CF[0-9]{6}/,
        ProjectNO: /(H|Z|S|T|M)CC[0-9]{6}-\d/,
        PN: /[A-Z]{5,8}/,
        Description: () => { return Mock.mock('@title(3, 5)') },
        AckDate: () => { return Mock.mock('@date("yyyy-MM-dd")') },
        ExpectDate: () => { return Mock.mock('@date("yyyy-MM-dd")') },
        OrderDate: () => { return Mock.mock('@date("yyyy-MM-dd")') },
        ReceiptNO: /(H|Z|S|T|M)RA[0-9]{6}/,
        ReceiptDate: () => { return Mock.mock('@date("yyyy-MM-dd")') },
        DaysDelay: () => { return Mock.mock('@integer(20, 365)') }
      }
    ]
  })

  return list.data
})

Mock.mock(RegExp('^(/Data/SupplierOpenAmount)' + '.*'), options => {
  console.debug('\u001b[35m' + '[Mocking] ', 'SupplierOpenAmount')

  const SupplierCode = getQueryParameter(options, 'SupplierCode')

  // list = {data:[{},{}]}
  const list = Mock.mock({
    'data|1-5': [
      {
        Site: /ZHU/,
        SupplierCode: SupplierCode,
        Currency: /(RMB|USD|EUR|HKD)/,
        'Amount|1000-2000': 1,
        'USD|1000-20000': 1,
        'Rate|6-9': 1
      }, {
        Site: /HKG/,
        SupplierCode: SupplierCode,
        Currency: /(RMB|USD|EUR|HKD)/,
        'Amount|1000-2000': 1,
        'USD|1000-20000': 1,
        'Rate|6-9': 1
      }, {
        Site: /TLS/,
        SupplierCode: SupplierCode,
        Currency: /(RMB|USD|EUR|HKD)/,
        'Amount|1000-2000': 1,
        'USD|1000-20000': 1,
        'Rate|6-9': 1
      }, {
        Site: /SGP/,
        SupplierCode: SupplierCode,
        Currency: /(RMB|USD|EUR|HKD)/,
        'Amount|1000-2000': 1,
        'USD|1000-20000': 1,
        'Rate|6-9': 1
      }, {
        Site: /MIA/,
        SupplierCode: SupplierCode,
        Currency: /(RMB|USD|EUR|HKD)/,
        'Amount|1000-2000': 1,
        'USD|1000-20000': 1,
        'Rate|6-9': 1
      }
    ]
  })

  return list.data
})

Mock.mock(RegExp('^(/Data/SupplierOpenItems)' + '.*'), options => {
  console.debug('\u001b[35m' + '[Mocking] ', 'SupplierOpenItems')

  const SupplierCode = getQueryParameter(options, 'SupplierCode')
  // list = {data:[{},{}]}
  const list = Mock.mock({
    'data|1-50': [
      {
        Site: /ZHU|HKG|TLS|SGP|MIA|CIN|QAT/,
        SupplierCode: SupplierCode,
        PurchaseNO: /(H|Z|S|T|M)CF[0-9]{6}/,
        ProjectNO: /(H|Z|S|T|M)CC[0-9]{6}-\d/,
        PN: /[A-Z]{5,8}/,
        Description: () => { return Mock.mock('@title(3, 5)') },
        AckDate: () => { return Mock.mock('@date("yyyy-MM-dd")') },
        ExpectDate: () => { return Mock.mock('@date("yyyy-MM-dd")') },
        OrderDate: () => { return Mock.mock('@date("yyyy-MM-dd")') },
        DaysDelay: () => { return Mock.mock('@integer(20, 365)') }
      }
    ]
  })

  return list.data
})

Mock.mock(RegExp('^(/Data/SupplierOpenQty)' + '.*'), options => {
  console.debug('\u001b[35m' + '[Mocking] ', 'SupplierOpenQty')

  const SupplierCode = getQueryParameter(options, 'SupplierCode')
  // list = {data:[{},{}]}
  const list = Mock.mock({
    'data|1-5': [
      {
        Site: /ZHU/,
        SupplierCode: SupplierCode,
        CountType: /Item|Product|Project/,
        Qty: () => { return Mock.mock('@integer(20, 10000)') }
      },
      {
        Site: /HKG/,
        SupplierCode: SupplierCode,
        CountType: /Item|Product|Project/,
        Qty: () => { return Mock.mock('@integer(20, 10000)') }
      },
      {
        Site: /TLS/,
        SupplierCode: SupplierCode,
        CountType: /Item|Product|Project/,
        Qty: () => { return Mock.mock('@integer(20, 10000)') }
      },
      {
        Site: /SGP/,
        SupplierCode: SupplierCode,
        CountType: /Item|Product|Project/,
        Qty: () => { return Mock.mock('@integer(20, 10000)') }
      },
      {
        Site: /MIA/,
        SupplierCode: SupplierCode,
        CountType: /Item|Product|Project/,
        Qty: () => { return Mock.mock('@integer(20, 10000)') }
      },
      {
        Site: /QAT/,
        SupplierCode: SupplierCode,
        CountType: /Item|Product|Project/,
        Qty: () => { return Mock.mock('@integer(20, 10000)') }
      },
      {
        Site: /CIN/,
        SupplierCode: SupplierCode,
        CountType: /Item|Product|Project/,
        Qty: () => { return Mock.mock('@integer(20, 10000)') }
      }
    ]
  })

  return list.data
})

Mock.mock(RegExp('^(/Data/SupplierTotalAmount)' + '.*'), options => {
  console.debug('\u001b[35m' + '[Mocking] ', 'SupplierTotalAmount')

  const SupplierCode = getQueryParameter(options, 'SupplierCode')
  // list = {data:[{},{}]}
  const list = Mock.mock({
    'data|1-5': [
      {
        Site: /ZHU/,
        SupplierCode: SupplierCode,
        Currency: /(RMB|USD|EUR|HKD)/,
        'Amount|1000-2000': 1,
        'USD|1000-20000': 1,
        'Rate|6-9': 1
      }, {
        Site: /HKG/,
        SupplierCode: SupplierCode,
        Currency: /(RMB|USD|EUR|HKD)/,
        'Amount|1000-2000': 1,
        'USD|1000-20000': 1,
        'Rate|6-9': 1
      }, {
        Site: /TLS/,
        SupplierCode: SupplierCode,
        Currency: /(RMB|USD|EUR|HKD)/,
        'Amount|1000-2000': 1,
        'USD|1000-20000': 1,
        'Rate|6-9': 1
      }, {
        Site: /SGP/,
        SupplierCode: SupplierCode,
        Currency: /(RMB|USD|EUR|HKD)/,
        'Amount|1000-2000': 1,
        'USD|1000-20000': 1,
        'Rate|6-9': 1
      }, {
        Site: /MIA/,
        SupplierCode: SupplierCode,
        Currency: /(RMB|USD|EUR|HKD)/,
        'Amount|1000-2000': 1,
        'USD|1000-20000': 1,
        'Rate|6-9': 1
      }
    ]
  })

  return list.data
})

Mock.mock(RegExp('^(/Data/SupplierTotalQty)' + '.*'), options => {
  console.debug('\u001b[35m' + '[Mocking] ', 'SupplierTotalQty')

  const SupplierCode = getQueryParameter(options, 'SupplierCode')
  // list = {data:[{},{}]}
  const list = Mock.mock({
    'data|1-5': [
      {
        Site: /ZHU/,
        SupplierCode: SupplierCode,
        CountType: /Item|Product|Project/,
        Qty: () => { return Mock.mock('@integer(20, 1000)') }
      },
      {
        Site: /HKG/,
        SupplierCode: SupplierCode,
        CountType: /Item|Product|Project/,
        Qty: () => { return Mock.mock('@integer(20, 1000)') }
      },
      {
        Site: /TLS/,
        SupplierCode: SupplierCode,
        CountType: /Item|Product|Project/,
        Qty: () => { return Mock.mock('@integer(20, 1000)') }
      },
      {
        Site: /SGP/,
        SupplierCode: SupplierCode,
        CountType: /Item|Product|Project/,
        Qty: () => { return Mock.mock('@integer(20, 1000)') }
      },
      {
        Site: /MIA/,
        SupplierCode: SupplierCode,
        CountType: /Item|Product|Project/,
        Qty: () => { return Mock.mock('@integer(20, 1000)') }
      },
      {
        Site: /QAT/,
        SupplierCode: SupplierCode,
        CountType: /Item|Product|Project/,
        Qty: () => { return Mock.mock('@integer(20, 1000)') }
      },
      {
        Site: /CIN/,
        SupplierCode: SupplierCode,
        CountType: /Item|Product|Project/,
        Qty: () => { return Mock.mock('@integer(20, 1000)') }
      }
    ]
  })

  return list.data
})
