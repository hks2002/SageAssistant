import { Cookies, LocalStorage, SessionStorage } from 'quasar'
import _merge from 'lodash/merge'

export const storageKey = {
  loginData: 'loginData',
  token: 'authorization'
}

// Cookies
export function setCookies (key, val) {
  Cookies.set(key, val, { expires: 24 * 3600 })
}

export function getCookies (key) {
  return Cookies.get(key)
}
// Cookies

// SessionStorage loginData
export function setLoginData(val) {
  SessionStorage.set(storageKey.loginData, val)
}

export function getLoginData() {
  return SessionStorage.getItem(storageKey.loginData)
}
// SessionStorage loginData

// SessionStorage token
export function setToken(val) {
  SessionStorage.set(storageKey.token, val)
}

export function getToken() {
  return SessionStorage.getItem(storageKey.token)
}

export function removeToken() {
  SessionStorage.remove(storageKey.token)
}
// SessionStorage token

export function getAll() {
  return _merge(LocalStorage.getAll(), Cookies.getAll())
}

export function getAllKeys() {
  return _merge(LocalStorage.getAllKeys(), _.keys(Cookies.getAll()))
}

export default {
  storageKey,
  setCookies,
  getCookies,
  getLoginData,
  setLoginData,
  getToken,
  setToken,
  removeToken,
  getAll,
  getAllKeys
}
