/***
 * @Author         : Robert Huang<56649783@qq.com>
 * @Date           : 2022-03-25 11:01:23
 * @LastEditors    : Robert Huang<56649783@qq.com>
 * @LastEditTime   : 2022-05-28 23:02:52
 * @FilePath       : \web2\src\assets\auth.js
 * @CopyRight      : Dedienne Aerospace China ZhuHai
 */

import { getSessionStorage, setSessionStorage } from '@/assets/storage'
import _indexOf from 'lodash/indexOf'

export function setAuthority(json) {
  const str = JSON.stringify(json)
  const authorizations = str.match(/sessions\?f=([A-Z]+)/g)
  for (let i = 0; i < authorizations.length; ++i) {
    authorizations[i] = authorizations[i].replace('sessions?f=', '')
  }
  setSessionStorage('authorizations', authorizations)
}

export function getAuthority() {
  return getSessionStorage('authorizations')
}

export function isAuthorised(fun) {
  if (process.env.DEV) {
    return true
  }
  const authorizations = getSessionStorage('authorizations')
  const index = _indexOf(authorizations, fun)
  if (index > -1) {
    return true
  } else {
    return false
  }
}

export default {
  setAuthority,
  getAuthority,
  isAuthorised
}
