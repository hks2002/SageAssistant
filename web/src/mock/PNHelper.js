import Mock from 'mockjs'
import { getUrlParam } from '../js/getUrlParam'

Mock.setup({
  timeout: '1000-3000'
})

Mock.mock(RegExp('^(/Data/PNHelper)' + '.*'), options => {
  const PnOrPnRoot = getUrlParam(options.url, 'PnOrPnRoot')

  // list = {data:[{},{}]}
  const list = Mock.mock({
    'data|1-10': [
      {
        PNROOT: PnOrPnRoot,
        PN: () => { return PnOrPnRoot + '_' + Mock.mock('@character("ABCDEFG")') }
      }
    ]
  })

  return list.data
})
