import { boot } from 'quasar/wrappers'
import axios from 'axios'

/// //////////////////////////////////////////////////////////////////////////
/// Set default for axioxs
/// /////////////////////////////////////////////////////////////////////////
/// in development, mock first
import { storageKey, getToken } from 'assets/storage'
import common from 'assets/common'

axios.defaults.headers['Content-Type'] = 'application/json;charset=utf-8'
axios.defaults.headers.accept = 'application/json'
axios.defaults.withCredentials = true // Allow send cookies
axios.defaults.timeout = 20000

// Set request interceptors
axios.interceptors.request.use(
  (config) => {
    console.debug('\u001b[31m' + '[Axios] ', 'Request config:')
    if (getToken()) {
      config.headers[storageKey.token] = getToken()
    }
    console.debug(config)
    return Promise.resolve(config)
  },
  (error) => {
    // It seems never happens, Most error handing please go to response interceptor
    console.debug('\u001b[31m' + '[Axios] ', 'Request error:')
    console.debug(error)
    return Promise.reject(error)
  }
)

// Set response interceptors
axios.interceptors.response.use(
  (response) => {
    console.debug('\u001b[31m' + '[Axios] ', 'Response success:')
    console.debug(response)
    return Promise.resolve(response)
  },
  (error) => {
    console.error('\u001b[31m' + '[Axios] ', 'Response error:')
    console.error(error)

    if (error.response) {
      if (parseInt(error.response.status / 100) === 4) {
        if (error.response.status === 401) {
          common.notifyWaring('You are not authorized')
        } else if (error.response.status === 403) {
          common.notifyWaring('You are not authorized')
        } else if (error.response.status === 404) {
          common.notifyError('Page not find')
        } else {
          common.notifyError('Request errors')
        }
      } else if (parseInt(error.response.status / 100) === 5) {
        common.notifyError('Server error')
      } else {
        common.notifyError('Server failed')
      }
    } else {
      common.notifyError(error.message)
      common.notifyError('Contact your admin')
    }

    return Promise.reject(error.response)
  }
)

export default boot(({ app }) => {
  app.config.globalProperties.$axios = axios
  // ^ ^ ^ this will allow you to use this.$axios (for Vue Options API form)<doesn't work in setup()>
  //       so you won't necessarily have to import axios in each vue file
  //       import { getCurrentInstance } from 'vue'
  //       const { proxy } = getCurrentInstance()
  //       proxy.$axios....
})

export { axios }
