import Mock from 'mockjs'
import { getUrlParam } from '../js/getUrlParam'

Mock.setup({
  timeout: '1000-3000'
})

Mock.mock(RegExp('^(/Data/PNsInFamily)' + '.*'), options => {
  let PnRoot = getUrlParam(options.url, 'PnRoot')

  // list = {data:[{},{}]}
  let list = Mock.mock({
    'data|1-10': [
      {
        'ROWID|+1': 1,
        PNROOT: PnRoot,
        PN: () => { return PnRoot + '_' + Mock.mock('@character("ABCDEFG")') },
        Cat: /(P|C|S)000[1-5]/,
        Version: /[A-Z]/,
        WC: /[A-Z][A-Z]/,
        Desc1: () => { return Mock.mock('@sentence(3, 5)') },
        Desc2: () => { return Mock.mock('@sentence(3, 5)') },
        Desc3: () => { return Mock.mock('@sentence(3, 5)') },
        'Status|1-2': 1,
        CreateDate: () => { return Mock.mock('@datetime("yyyy-MM-dd")') }
      }
    ]
  })

  return list.data
})
