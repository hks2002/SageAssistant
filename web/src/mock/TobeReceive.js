import Mock from 'mockjs'

Mock.setup({
  timeout: '1000-3000'
})

Mock.mock(RegExp('^(/Data/TobeReceive)' + '.*'), options => {
  // list = {data:[{},{}]}
  let list = Mock.mock({
    'data|1-100': [
      {
        'ProjectNO': /(Z|H|S|T|M)CC\d{6}-\d{1,2}/,
        'PurchaseNO': /(Z|H|S|T|M)CF\d{6}/,
        'Line|1-100': 1,
        'VendorCode': /0000\d{ 1}/,
        'VendorName': () => { return Mock.mock('@sentence(3, 5)') },
        'PN': /([A-Z]){6}/,
        'Qty|1-100': 1,
        'Unit': 'UN',
        'Description': () => { return Mock.mock('@sentence(3, 5)') },
        'NetPrice|1000-2000': 1,
        'Currency': /(RMB|USD|EUR|HKD)/,
        'RMB|10-200000': 1,
        'Rate|6-9': 1,
        'AckDate': () => { return Mock.mock('@date("yyyy-MM-dd")') },
        'ExpectDate': () => { return Mock.mock('@date("yyyy-MM-dd")') },
        'OrderDate': () => { return Mock.mock('@date("yyyy-MM-dd")') },
        'CreateUser': /0000[A-D]{1}/
      }
    ]
  })
  return list.data
})
