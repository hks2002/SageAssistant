import Mock from 'mockjs'
import { mockData, getPostParameters } from '../mockExt'

const login = (options) => {
  console.debug('\u001b[35m' + '[Mocking] ', 'login')
  const postdata = getPostParameters(options)

  if (postdata.username === 'admin' || postdata.username === 'test') {
    return {}
  } else {
    return mockData(
      { $diagnoses: [{ $message: 'Wrong username and password' }] },
      401
    )
  }
}
// After tested the failed case, comment it
Mock.mock(/\/auth\/login\/submit/, 'post', login)

Mock.mock(/^\/api1\/syracuse\/collaboration\/syracuse\/userProfiles\/\$template\/\$workingCopies/, options => {
  console.debug('\u001b[35m' + '[Mocking] ', 'workingCopies')
  const loginData = { user: {}, selectedEndpoint: {}, selectedRole: {}, selectedLocale: {} }
  loginData.user.firstName = Mock.mock('@cname')
  loginData.user.lastName = Mock.mock('@cname')
  loginData.user.email = Mock.mock('@email')
  loginData.productName = Mock.mock('@title')
  loginData.selectedEndpoint.description = Mock.mock('@sentence(3, 5)')
  loginData.user.$uuid = Mock.mock('@guid')
  loginData.selectedRole.$uuid = Mock.mock('@guid')
  loginData.selectedLocale.code = Mock.mock('@county(true)')
  loginData.selectedLocale.description = Mock.mock('@sentence(3, 5)')
  return loginData
})

Mock.mock(/^\/api1\/syracuse\/collaboration\/syracuse\/pages\('x3\.erp\.EXPLOIT\.home\.\$navigation'\)/, options => {
  console.debug('\u001b[35m' + '[Mocking] ', 'navigation')
  const str = {
    menuBlock: {
      $url1: '{$transMenuBaseUrl}/$sessions?f=' + Mock.mock(/[A-Z]{5}/) + '%2F2%2F%2FM%2F',
      $url2: '{$transMenuBaseUrl}/$sessions?f=' + Mock.mock(/[A-Z]{5}/) + '%2F2%2F%2FM%2F',
      $url3: '{$transMenuBaseUrl}/$sessions?f=' + Mock.mock(/[A-Z]{5}/) + '%2F2%2F%2FM%2F',
      $url4: '{$transMenuBaseUrl}/$sessions?f=' + Mock.mock(/[A-Z]{5}/) + '%2F2%2F%2FM%2F',
      $url5: '{$transMenuBaseUrl}/$sessions?f=' + Mock.mock(/[A-Z]{5}/) + '%2F2%2F%2FM%2F',
      $url6: '{$transMenuBaseUrl}/$sessions?f=' + Mock.mock(/[A-Z]{5}/) + '%2F2%2F%2FM%2F'
    }
  }
  return str
})
