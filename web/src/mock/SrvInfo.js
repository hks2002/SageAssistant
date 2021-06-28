import Mock from 'mockjs'

Mock.setup({
  timeout: 200
})
const reg = /^(\/Data\/SrvInfo)/

Mock.mock(reg, {
  name: 'MockData',
  version: '0.0.0',
  springBootVersion: '0.0.0'
})
