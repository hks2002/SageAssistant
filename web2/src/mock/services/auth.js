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
// Mock.mock(/\/auth\/login\/submit/, 'post', login)
