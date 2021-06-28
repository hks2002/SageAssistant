import Mock from 'mockjs'
import { getUrlParam } from '../js/getUrlParam'

Mock.setup({
  timeout: '1000-3000'
})

Mock.mock(RegExp('^(/Data/SupplyHelper)' + '.*'), options => {
  const CodeOrName = getUrlParam(options.url, 'SupplyCodeOrName')

  // list = {data:[{},{}]}
  const list = Mock.mock({
    'data|1-10': [
      {
        SupplyCode: CodeOrName,
        SupplyName: () => { return CodeOrName + '_' + Mock.mock('@title(3, 5)') }
      }
    ]
  })

  return list.data
})
