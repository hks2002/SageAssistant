import Mock from 'mockjs'
import { getUrlParam } from '../js/getUrlParam'

Mock.setup({
  timeout: '1000-3000'
})

Mock.mock(RegExp('^(/Data/PNHelper)' + '.*'), options => {
  let PnOrPnRoot = getUrlParam(options.url, 'PnOrPnRoot')

  // list = {data:[{},{}]}
  let list = Mock.mock({
    'data|1-10': [
      {
        PNROOT: PnOrPnRoot,
        PN: () => { return PnOrPnRoot + '_' + Mock.mock('@character("ABCDEFG")') }
      }
    ]
  })

  return list.data
})
