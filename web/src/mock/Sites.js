import Mock from 'mockjs'
const reg = /^(\/Data\/Sites)/

Mock.mock(reg, [
  'ZHU',
  'HKG',
  'SGP',
  'TLS',
  'MIA',
  'AAA'
])
