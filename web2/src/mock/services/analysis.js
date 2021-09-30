import Mock from 'mockjs'
import { orderBy } from 'lodash'
import _forEach from 'lodash/forEach'
import { getQueryParameter } from '../mockExt'

Mock.mock(RegExp('^(/Data/AnalysisQuoteSalesCost)' + '.*'), options => {
  console.debug('\u001b[35m' + '[Mocking] ', 'AnalysisQuoteSalesCost')

  const Site = getQueryParameter(options, 'Site')

  // list = {data:[{},{}]}
  const list = Mock.mock({
    'data|1-1000': [
      {
        Site: /(ZHU|HKG|SGP|TLS|MIA)/,
        PN: /[0-9A-Z]{5,8}/,
        Description: () => { return Mock.mock('@title(5, 10)') },
        'QCnt|1-10': 1,
        'QQty|1-10': 1,
        MinQPrice: () => { return Mock.mock('@float(60, 10000, 3, 5)') },
        AvgQPrice: () => { return Mock.mock('@float(60, 10000, 3, 5)') },
        MaxQPrice: () => { return Mock.mock('@float(60, 10000, 3, 5)') },
        LastQPrice1: () => { return Mock.mock('@float(60, 10000, 3, 5)') },
        LastQPrice2: () => { return Mock.mock('@float(60, 10000, 3, 5)') },
        LastQPrice3: () => { return Mock.mock('@float(60, 10000, 3, 5)') },
        'SCnt|1-10': 1,
        'SQty|1-10': 1,
        MinSPrice: () => { return Mock.mock('@float(60, 10000, 3, 5)') },
        AvgSPrice: () => { return Mock.mock('@float(60, 10000, 3, 5)') },
        MaxSPrice: () => { return Mock.mock('@float(60, 10000, 3, 5)') },
        LastSPrice1: () => { return Mock.mock('@float(60, 10000, 3, 5)') },
        LastSPrice2: () => { return Mock.mock('@float(60, 10000, 3, 5)') },
        LastSPrice3: () => { return Mock.mock('@float(60, 10000, 3, 5)') },
        LastPJT1: /(H|Z|S|T|M)CC[0-9]{6}-[1-9]{1}/,
        LastPJT2: /(H|Z|S|T|M)CC[0-9]{6}-[1-9]{1}/,
        LastPJT3: /(H|Z|S|T|M)CC[0-9]{6}-[1-9]{1}/,
        LastCost1: () => { return Mock.mock('@float(60, 10000, 3, 5)') },
        LastCost2: () => { return Mock.mock('@float(60, 10000, 3, 5)') },
        LastCost3: () => { return Mock.mock('@float(60, 10000, 3, 5)') }
      }
    ]
  })

  return list.data
})
