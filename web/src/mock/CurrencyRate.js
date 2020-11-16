import Mock from 'mockjs'
import { getUrlParam } from '../js/getUrlParam'

Mock.setup({
  timeout: '1000-3000'
})

Mock.mock(RegExp('^(/Data/CurrencyRateBatch)' + '.*'), options => {
  const query = getUrlParam(options.url, 'query')

  const allq = query.split('_')
  const allres = []

  for (let i = 0, l = allq.length; i < l; i++) {
    const sour = allq[i].substring(0, 3)
    if (sour === 'USD') {
      allres[i] = Mock.mock('@float(6, 8.5, 3, 5)')
    } else if (sour === 'EUR') {
      allres[i] = Mock.mock('@float(6.5, 9.6, 3, 5)')
    } else if (sour === 'HKD') {
      allres[i] = Mock.mock('@float(0.78, 0.94, 3, 5)')
    } else {
      allres[i] = 1
    }
  }

  return allres
})
