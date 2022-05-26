import Mock from 'mockjs'

Mock.mock(/^\/Data\/Sites$/, () => {
  console.debug('\u001b[35m' + '[Mocking] ', 'Sites')
  return ['ZHU', 'HKG', 'SGP', 'TLS', 'MIA', 'AAA']
})
