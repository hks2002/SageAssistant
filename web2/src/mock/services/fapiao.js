/***
 * @Author         : Robert Huang<56649783@qq.com>
 * @Date           : 2022-03-25 11:01:23
 * @LastEditors    : Robert Huang<56649783@qq.com>
 * @LastEditTime   : 2022-05-28 23:16:50
 * @FilePath       : \web2\src\mock\services\fapiao.js
 * @CopyRight      : Dedienne Aerospace China ZhuHai
 */
import { getQueryParameters } from '@/assets/mockExt'
import Mock from 'mockjs'

// 发票代码
Mock.mock(RegExp('^(/Fapiao/Lbdm)' + '.*'), (options) => {
  console.debug('\u001b[35m' + '[Mocking] ', 'fapiaoLbdm')

  const query = getQueryParameters(options)
  const Lbdm = query['Lbdm']
  // list = {data:[]}
  const list = Mock.mock({
    'data|1-10': [
      () => {
        if (Lbdm.length >= 10) {
          return Lbdm.substring(0, 10)
        } else {
          return Lbdm + Mock.mock(/\d{8}/).substring(0, 10 - Lbdm.length)
        }
      }
    ]
  })

  return list.data
})

// 发票号码
Mock.mock(RegExp('^(/Fapiao/Fphm)' + '.*'), (options) => {
  console.debug('\u001b[35m' + '[Mocking] ', 'fapiaoFphm')

  const query = getQueryParameters(options)
  const Fphm = query['Fphm']
  // list = {data:[]}
  const list = Mock.mock({
    'data|1-10': [
      () => {
        if (Fphm.length >= 8) {
          return Fphm.substring(0, 8)
        } else {
          return Fphm + Mock.mock(/\d{8}/).substring(0, 8 - Fphm.length)
        }
      }
    ]
  })

  return list.data
})

Mock.mock(RegExp('^(/Fapiao/Header)' + '.*'), (options) => {
  console.debug('\u001b[35m' + '[Mocking] ', 'fapiaoHeader')

  const query = getQueryParameters(options)
  const Lbdm = query['Lbdm']
  const Fphm = query['Fphm']
  // list = {data:[{}]}
  const list = Mock.mock({
    'data|1-2': [
      {
        rowid: 1,
        fpzl: /(专用发票|普通发票)/,
        lbdm: Lbdm,
        fphm: Fphm,
        kprq: () => {
          return Mock.mock('@date')
        },
        gfmc: () => {
          return Mock.mock('@ctitle(5, 10)')
        },
        gfsh: () => {
          return Mock.mock('@string("number", 10)')
        },
        gfyhzh: () => {
          return Mock.mock('@csentence') + Mock.mock('@ctitle(5, 10)')
        },
        gfdzdh: () => {
          return Mock.mock('@county(true)') + Mock.mock('@csentence')
        },
        xfmc: () => {
          return Mock.mock('@ctitle(5, 10)')
        },
        xfsh: () => {
          return Mock.mock('@string("number", 10)')
        },
        xfyhzh: () => {
          return Mock.mock('@csentence') + Mock.mock('@ctitle(5, 10)')
        },
        xfdzdh: () => {
          return Mock.mock('@county(true)') + Mock.mock('@csentence')
        },
        hjje: () => {
          return Mock.mock('@float(60, 10000, 3, 5)')
        },
        hjse: () => {
          return Mock.mock('@float(60, 10000, 3, 5)')
        },
        bz: () => {
          return Mock.mock('@sentence(3, 5)')
        },
        kpr: () => {
          return Mock.mock('@cname')
        },
        fhr: () => {
          return Mock.mock('@cname')
        },
        skr: () => {
          return Mock.mock('@cname')
        },
        fpzt: /(正常发票|作废发票)/,
        checkDate: Mock.mock('@date')
      }
    ]
  })

  return list.data
})

Mock.mock(RegExp('^(/Fapiao/Body)' + '.*'), (options) => {
  console.debug('\u001b[35m' + '[Mocking] ', 'fapiaoBody')

  const query = getQueryParameters(options)
  const Lbdm = query['Lbdm']
  const Fphm = query['Fphm']
  // list = {data:[{},{}]}
  const list = Mock.mock({
    'data|1-10': [
      {
        rowid: 1,
        lbdm: Lbdm,
        fphm: Fphm,
        xh: () => {
          return Mock.mock({ 'number|1-100': 100 }).number
        },
        spmc: () => {
          return Mock.mock('@ctitle(5, 10)')
        },
        ggxh: () => {
          return Mock.mock('@string("number", 10)')
        },
        jldw: () => {
          return Mock.mock('@cword')
        },
        dj: () => {
          return Mock.mock({ 'number|1-100': 100 }).number
        },
        sl: () => {
          return Mock.mock({ 'number|1-100': 100 }).number
        },
        je: () => {
          return Mock.mock({ 'number|1-100': 100 }).number
        },
        slv: /(0|13)%/,
        se: () => {
          return Mock.mock({ 'number|1-100': 100 }).number
        }
      }
    ]
  })

  return list.data
})
