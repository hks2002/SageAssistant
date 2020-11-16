import Mock from 'mockjs'

Mock.setup({
  timeout: '1000-3000'
})

Mock.mock(RegExp('^(/Data/TobeDealWithOrderLine)' + '.*'), options => {
  // list = {data:[{},{}]}
  const list = Mock.mock({
    'data|1-100': [
      {
        SalesOrderNO: /(Z|H|S|T|M)CC\d{6}/,
        ProjectNO: /(Z|H|S|T|M)CC\d{6}-\d{1,2}/,
        PN: /([A-Z]){6}/,
        Description: () => { return Mock.mock('@sentence(3, 5)') },
        'Qty|1-100': 1,
        Unit: 'UN',
        OrderDate: () => { return Mock.mock('@date("yyyy-MM-dd")') },
        OrderType: /(NOR|REP|PRS|RCL)/,
        DemandDate: () => { return Mock.mock('@date("yyyy-MM-dd")') },
        CustomerCode: /\d{5}/,
        CustomerName: () => { return Mock.mock('@sentence(3, 5)') },
        OrderCategory: /(Methods|InteralProd|ExternalProd|Design|Stock|Trading|Services|SalesAdmin)/
      }
    ]
  })
  return list.data
})
