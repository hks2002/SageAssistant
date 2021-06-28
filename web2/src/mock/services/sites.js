import Mock from 'mockjs'

Mock.mock(/^\/Data\/Sites$/, options => {
  console.debug('\u001b[35m' + '[Mocking] ', 'Sites')
  return ['ZHU', 'HKG', 'SGP', 'TLS', 'MIA', 'AAA']
})
