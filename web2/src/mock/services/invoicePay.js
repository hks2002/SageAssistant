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

Mock.mock(RegExp('^(/Data/FinancialInvoicePay)' + '.*'), (options) => {
  console.debug('\u001b[35m' + '[Mocking] ', 'FinancialInvoicePay')

  const query = getQueryParameters(options)
  const CustomerCode = query['CustomerCode']

  // list = {data:[{},{}]}
  const list = Mock.mock({
    'data|1-500': [
      {
        Site: /ZHU|HKG|TLS|SGP|MIA|CIN|QAT/,
        Customer: CustomerCode,
        Name: () => {
          return Mock.mock('@title(3, 10)')
        },
        InvoiceNO: /(H|Z|S|T|M)FC[0-9]{6}/,
        Currency: /RMB|USD/,
        'Amount|1000-2000': 1,
        'AmountLocal|1000-2000': 1,
        'Pay|1000-2000': 1,
        'PayLocal|1000-2000': 1,
        OrderNO: /(H|Z|S|T|M)CC[0-9]{6}/,
        CreateDate: () => {
          return Mock.mock('@date("yyyy-MM-dd")')
        },
        DueDate: () => {
          return Mock.mock('@date("yyyy-MM-dd")')
        },
        PayDate: () => {
          return Mock.mock('@date("yyyy-MM-dd")')
        },
        Fapiao: /Fapiao-[0-9]{8}/,
        CustRef: /(H|Z|S|T|M)XXX[0-9]{6}/,
        Status: /Paid|P-Paid/
      }
    ]
  })

  return list.data
})

Mock.mock(RegExp('^(/Data/FinancialInvoicePayPro)' + '.*'), (options) => {
  console.debug('\u001b[35m' + '[Mocking] ', 'FinancialInvoicePay')

  const query = getQueryParameters(options)
  const CustomerCode = query['CustomerCode']

  // list = {data:[{},{}]}
  const list = Mock.mock({
    'data|1-500': [
      {
        Site: /ZHU|HKG|TLS|SGP|MIA|CIN|QAT/,
        Customer: CustomerCode,
        Name: () => {
          return Mock.mock('@title(3, 10)')
        },
        InvoiceNO: /(H|Z|S|T|M)FC[0-9]{6}/,
        Currency: /RMB|USD/,
        'Amount|1000-2000': 1,
        'AmountLocal|1000-2000': 1,
        'Pay|1000-2000': 1,
        'PayLocal|1000-2000': 1,
        OrderNO: /(H|Z|S|T|M)CC[0-9]{6}/,
        CreateDate: () => {
          return Mock.mock('@date("yyyy-MM-dd")')
        },
        DueDate: () => {
          return Mock.mock('@date("yyyy-MM-dd")')
        },
        PayDate: () => {
          return Mock.mock('@date("yyyy-MM-dd")')
        },
        Fapiao: /Fapiao-[0-9]{8}/,
        CustRef: /(H|Z|S|T|M)XXX[0-9]{6}/,
        Status: /Paid|P-Paid/,
        MatchedBy: /(FCCR_A|FCCR_M|FCAC|FCODODCR)/,
        PayNO: /(ZCBC|BOC)[0-9]{10}/,
        PayCurrency: /RMB|USD/,
        'PayInPayNO|1000-2000': 1,
        Desc0: () => {
          return Mock.mock('@title(3, 10)')
        },
        Desc1: () => {
          return Mock.mock('@title(3, 10)')
        }
      }
    ]
  })

  return list.data
})
