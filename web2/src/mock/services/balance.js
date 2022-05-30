/***
 * @Author         : Robert Huang<56649783@qq.com>
 * @Date           : 2022-04-01 10:30:43
 * @LastEditors    : Robert Huang<56649783@qq.com>
 * @LastEditTime   : 2022-05-28 23:17:00
 * @FilePath       : \web2\src\mock\services\financials.js
 * @CopyRight      : Dedienne Aerospace China ZhuHai
 */
import { getQueryParameters } from '@/assets/mockExt'
import Mock from 'mockjs'

Mock.mock(RegExp('^(/Data/FinancialBalanceC)' + '.*'), (options) => {
  console.debug('\u001b[35m' + '[Mocking] ', 'FinancialBalanceC')

  const query = getQueryParameters(options)
  const Year = query['Year']
  const AccountNO = query['AccountNO']

  // list = {data:[{},{}]}
  const list = Mock.mock({
    'data|1-5': [
      {
        AccountNO: () => {
          return AccountNO + Mock.mock('@character')
        },
        Currency: /RMB|USD/,
        Year: Year,
        'C0|1000-20000000': 1,
        'C1|1000-20000000': 1,
        'C2|1000-20000000': 1,
        'C3|1000-20000000': 1,
        'C4|1000-20000000': 1,
        'C5|1000-20000000': 1,
        'C6|1000-20000000': 1,
        'C7|1000-20000000': 1,
        'C8|1000-20000000': 1,
        'C9|1000-20000000': 1,
        'C10|1000-20000000': 1,
        'C11|1000-20000000': 1,
        'C12|1000-20000000': 1
      }
    ]
  })

  return list.data
})

Mock.mock(RegExp('^(/Data/FinancialBalanceD)' + '.*'), (options) => {
  console.debug('\u001b[35m' + '[Mocking] ', 'FinancialBalanceD')

  const query = getQueryParameters(options)
  const Year = query['Year']
  const AccountNO = query['AccountNO']

  // list = {data:[{},{}]}
  const list = Mock.mock({
    'data|1-5': [
      {
        AccountNO: () => {
          return AccountNO + Mock.mock('@character')
        },
        Currency: /RMB|USD/,
        Year: Year,
        'D0|1000-20000000': 1,
        'D1|1000-20000000': 1,
        'D2|1000-20000000': 1,
        'D3|1000-20000000': 1,
        'D4|1000-20000000': 1,
        'D5|1000-20000000': 1,
        'D6|1000-20000000': 1,
        'D7|1000-20000000': 1,
        'D8|1000-20000000': 1,
        'D9|1000-20000000': 1,
        'D10|1000-20000000': 1,
        'D11|1000-20000000': 1,
        'D12|1000-20000000': 1
      }
    ]
  })

  return list.data
})

Mock.mock(RegExp('^(/Data/FinancialBalanceM)' + '.*'), (options) => {
  console.debug('\u001b[35m' + '[Mocking] ', 'FinancialBalanceM')

  const query = getQueryParameters(options)
  const Year = query['Year']
  const AccountNO = query['AccountNO']

  // list = {data:[{},{}]}
  const list = Mock.mock({
    'data|1-5': [
      {
        AccountNO: () => {
          return AccountNO + Mock.mock('@character')
        },
        Currency: /RMB|USD/,
        Year: Year,
        'M0|1000-20000000': 1,
        'M1|1000-20000000': 1,
        'M2|1000-20000000': 1,
        'M3|1000-20000000': 1,
        'M4|1000-20000000': 1,
        'M5|1000-20000000': 1,
        'M6|1000-20000000': 1,
        'M7|1000-20000000': 1,
        'M8|1000-20000000': 1,
        'M9|1000-20000000': 1,
        'M10|1000-20000000': 1,
        'M11|1000-20000000': 1,
        'M12|1000-20000000': 1
      }
    ]
  })

  return list.data
})

Mock.mock(RegExp('^(/Data/FinancialBalanceB)' + '.*'), (options) => {
  console.debug('\u001b[35m' + '[Mocking] ', 'FinancialBalanceB')

  const query = getQueryParameters(options)
  const Year = query['Year']
  const AccountNO = query['AccountNO']

  // list = {data:[{},{}]}
  const list = Mock.mock({
    'data|1-5': [
      {
        AccountNO: () => {
          return AccountNO + Mock.mock('@character')
        },
        Currency: /RMB|USD/,
        Year: Year,
        'B0|1000-20000000': 1,
        'B1|1000-20000000': 1,
        'B2|1000-20000000': 1,
        'B3|1000-20000000': 1,
        'B4|1000-20000000': 1,
        'B5|1000-20000000': 1,
        'B6|1000-20000000': 1,
        'B7|1000-20000000': 1,
        'B8|1000-20000000': 1,
        'B9|1000-20000000': 1,
        'B10|1000-20000000': 1,
        'B11|1000-20000000': 1,
        'B12|1000-20000000': 1
      }
    ]
  })

  return list.data
})

Mock.mock(RegExp('^(/Data/FinancialBalance)' + '.*'), () => {
  console.debug('\u001b[35m' + '[Mocking] ', 'FinancialBalance')

  //const AccountNO = getQueryParameter(options, 'AccountNO')
  //const Year = getQueryParameter(options, 'Year')
  //const Month = getQueryParameter(options, 'Month')

  // list = {data:[{},{}]}
  const list = Mock.mock({
    'data|1-500': [
      {
        Site: /ZHU|HKG|TLS|SGP|MIA|CIN|QAT/,
        AccountNO: /[0-9]{6}/,
        Currency: /RMB|USD/,
        'Year|2000-2020': 1,
        'M0|0-20000': 1,
        'M1|0-20000': 1,
        'M2|0-20000': 1,
        'M3|0-20000': 1,
        'M4|0-20000': 1,
        'M5|0-20000': 1,
        'M6|0-20000': 1,
        'M7|0-20000': 1,
        'M8|0-20000': 1,
        'M9|0-20000': 1,
        'M10|0-20000': 1,
        'M11|0-20000': 1,
        'M12|0-20000': 1,
        'B0|0-20000': 1,
        'B1|0-20000': 1,
        'B2|0-20000': 1,
        'B3|0-20000': 1,
        'B4|0-20000': 1,
        'B5|0-20000': 1,
        'B6|0-20000': 1,
        'B7|0-20000': 1,
        'B8|0-20000': 1,
        'B9|0-20000': 1,
        'B10|0-20000': 1,
        'B11|0-20000': 1,
        'B12|0-20000': 1,
        'C0|0-20000': 1,
        'C1|0-20000': 1,
        'C2|0-20000': 1,
        'C3|0-20000': 1,
        'C4|0-20000': 1,
        'C5|0-20000': 1,
        'C6|0-20000': 1,
        'C7|0-20000': 1,
        'C8|0-20000': 1,
        'C9|0-20000': 1,
        'C10|0-20000': 1,
        'C11|0-20000': 1,
        'C12|0-20000': 1,
        'D0|0-20000': 1,
        'D1|0-20000': 1,
        'D2|0-20000': 1,
        'D3|0-20000': 1,
        'D4|0-20000': 1,
        'D5|0-20000': 1,
        'D6|0-20000': 1,
        'D7|0-20000': 1,
        'D8|0-20000': 1,
        'D9|0-20000': 1,
        'D10|0-20000': 1,
        'D11|0-20000': 1,
        'D12|0-20000': 1
      }
    ]
  })

  return list.data
})
