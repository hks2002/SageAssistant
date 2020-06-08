import Mock from 'mockjs'

Mock.setup({
  timeout: 200
})

Mock.mock(RegExp('^(/Data/SrvInfo)$'), {
  name: 'MockData',
  version: '0.0.0',
  springBootVersion: '0.0.0'
})

Mock.mock(RegExp('^(/Data/SrvInfo2)$'), {
  name: 'MockData2',
  version: '1.1.1',
  springBootVersion: '1.1.1'
})
