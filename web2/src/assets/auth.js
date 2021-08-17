
import { setSessionStorage, getSessionStorage } from 'assets/storage'

export function setAuthority (json) {
  const str = JSON.stringify(json)
  const authorizations = str.match(/sessions\?f=([A-Z]+)/g)
  for (let i = 0; i < authorizations.length; ++i) {
    authorizations[i] = authorizations[i].replace('sessions?f=', '')
  }
  setSessionStorage('authorizations', authorizations)
}

export function getAuthority () {
  return getSessionStorage('authorizations')
}

export function isAuthorised (fun) {
  if (process.env.DEV) {
    return true
  }
  const authorizations = getSessionStorage('authorizations')
  for (auth in authorizations) {
    if (fun === auth) {
      return true
    }
  }
  return false
}

export default {
  setAuthority,
  getAuthority,
  isAuthorised
}
