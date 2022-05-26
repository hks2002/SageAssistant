import { getQueryParameters } from '@/assets/mockExt'
import _forEach from 'lodash/forEach'
import Mock from 'mockjs'

Mock.mock(RegExp('^(/Data/StockSummary)' + '.*'), () => {
  console.debug('\u001b[35m' + '[Mocking] ', 'StockSummary')

  // const Site = getQueryParameter(options, 'site')

  // list = {data:[{},{}]}
  const list = Mock.mock({
    'data|1-1000': [
      {
        G: /[A-Z]/,
        A: '',
        Location: /[A-Z]MAG\d{2}/,
        PN: /[0-9A-Z]{5,8}/,
        OptionPN: /(123|456)/,
        Description: () => {
          return Mock.mock('@title(5, 10)')
        },
        'Qty|1-10': 1,
        Cost: () => {
          return Mock.mock('@float(60, 10000, 3, 5)')
        }
      }
    ]
  })
  _forEach(list.data, (value) => {
    value.A = value.PN.slice(0, 1)
  })

  return list.data
})

Mock.mock(RegExp('^(/Data/StockHistory)' + '.*'), (options) => {
  console.debug('\u001b[35m' + '[Mocking] ', 'StockHistory')

  // const Site = getQueryParameter(options, 'site')
  const PnOrName = getQueryParameters(options, 'PnOrName')
  const DateFrom = getQueryParameters(options, 'DateFrom')
  const DateTo = getQueryParameters(options, 'DateTo')

  // list = {data:[{},{}]}
  if (DateFrom || DateTo) {
    const list = Mock.mock({
      'data|1-2000': [
        {
          Location: /[A-Z]MAG\d{2}/,
          Seq: () => {
            return Mock.mock('@increment(1000)')
          },
          PN: () => {
            return PnOrName + Mock.mock('@word')
          },
          Description: () => {
            return PnOrName + Mock.mock('@title(3, 5)')
          },
          'Qty|1-10': 1,
          Unit: 'UN',
          Cost: () => {
            return Mock.mock('@float(60, 10000, 3, 5)')
          },
          ProjectNO: /(H|Z|S|T|M)CC[0-9]{6}-[1-9]{1}/,
          SourceNO: /(H|Z|S|T|M)CF[0-9]{6}/,
          SourceLine: () => {
            return Mock.mock({ 'number|1-100': 100 }).number * 1000
          },
          EntryNO: /(H|Z|S|T|M)RA[0-9]{6}/,
          EntryLine: () => {
            return Mock.mock({ 'number|1-100': 100 }).number * 1000
          },
          CreateUser: () => {
            return Mock.mock('@cname')
          },
          CreateDate: () => {
            return Mock.mock('@date("yyyy-MM-dd")')
          }
        }
      ]
    })
    return list.data
  } else {
    const list = Mock.mock({
      'data|1-20': [
        {
          Location: /[A-Z]MAG\d{2}/,
          Seq: () => {
            return Mock.mock('@increment(1000)')
          },
          'Qty|1-10': 1,
          Cost: () => {
            return Mock.mock('@float(60, 10000, 3, 5)')
          },
          ProjectNO: /(H|Z|S|T|M)CC[0-9]{6}-[1-9]{1}/,
          SourceNO: /(H|Z|S|T|M)CF[0-9]{6}/,
          SourceLine: () => {
            return Mock.mock({ 'number|1-100': 100 }).number * 1000
          },
          EntryNO: /(H|Z|S|T|M)RA[0-9]{6}/,
          EntryLine: () => {
            return Mock.mock({ 'number|1-100': 100 }).number * 1000
          },
          CreateUser: () => {
            return Mock.mock('@cname')
          },
          CreateDate: () => {
            return Mock.mock('@date("yyyy-MM-dd")')
          }
        }
      ]
    })

    return list.data
  }
})
