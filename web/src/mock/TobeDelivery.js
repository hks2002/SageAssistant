import Mock from 'mockjs'

Mock.setup({
  timeout: '1000-3000'
})

Mock.mock(RegExp('^(/Data/TobeDelivery)' + '.*'), options => {
  // list = {data:[{},{}]}

  let list = Mock.mock({
    'data|1-100': [
      {
        'ProjectNO': /(Z|H|S|T|M)CC\d{6}-\d{1,2}/,
        'OrderType': /(NOR|REP|PRS|RCL)/,
        'PN': /([A-Z]){6}/,
        'Qty|1-100': 1,
        'Description': () => { return Mock.mock('@sentence(3, 5)') },
        'CustomerCode': /\d{5}/,
        'CustomerName': () => { return Mock.mock('@sentence(3, 5)') },
        'Currency': /(RMB|USD|EUR|HKD)/,
        'NetPrice|1000-2000': 1,
        'RMB|10-200000': 1,
        'Rate|6-9': 1,
        'OrderDate': () => { return Mock.mock('@date("yyyy-MM-dd")') },
        'RequestDate': () => { return Mock.mock('@date("yyyy-MM-dd")') }
      }
    ]
  })
  return list.data
})
