import Mock from 'mockjs'

Mock.setup({
  timeout: '100-1000'
})

Mock.mock(RegExp('^(/Data/TobePurchaseBom)' + '.*'), options => {
  // list = {data:[{},{}]}
  const list = Mock.mock({
    'data|1-100': [
      {
        ProjectNO: /(Z|H|S|T|M)CC\d{6}-\d{1,2}/,
        OrderType: /(NOR|REP|PRS|RCL)/,
        WorkOrderNO: /(Z|H|S|T|M)OF\d{8}/,
        'BomSeq|1-100': 1,
        CustomerCode: /0000\d{1}/,
        CustomerName: () => { return Mock.mock('@sentence(3, 5)') },
        ForPN: /([A-Z]){6}/,
        PN: /([A-Z]){6}/,
        Description: () => { return Mock.mock('@sentence(3, 5)') },
        'Qty|1-100': 1,
        'ShortQty|1-100': 1,
        'AllQty|1-100': 1,
        Unit: 'UN',
        CreateDate: () => { return Mock.mock('@date("yyyy-MM-dd")') }
      }
    ]
  })
  return list.data
})
