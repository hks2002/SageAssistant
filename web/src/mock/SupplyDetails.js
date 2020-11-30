import Mock from 'mockjs'
import { getUrlParam } from '../js/getUrlParam'

Mock.setup({
  timeout: '1000-3000'
})

Mock.mock(RegExp('^(/Data/SupplyDetails)' + '.*'), options => {
  const SupplyCode = getUrlParam(options.url, 'SupplyCode')

  // list = {data:[{},{}]}
  const list = Mock.mock({
    'data|1': [
      {
        SupplyCode: SupplyCode,
        SupplyName0: () => { return Mock.mock('@title(3, 5)') },
        SupplyName1: () => { return Mock.mock('@title(3, 5)') },
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
