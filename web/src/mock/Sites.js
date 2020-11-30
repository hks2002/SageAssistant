import Mock from 'mockjs'

Mock.mock(RegExp('^(/Data/Sites)'), [
  'ZHU',
  'HKG',
  'SGP',
  'TLS',
  'MIA',
  'AAA'
])
