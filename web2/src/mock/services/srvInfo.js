import Mock from 'mockjs'

Mock.mock(/^\/Data\/SrvInfo$/, options => {
  console.debug('\u001b[35m' + '[Mocking] ', 'SrvInfo')
  return {
    name: 'MockData',
    version: '0.0.0',
    springBootVersion: '0.0.0'
  }
})
