/***
 * @Author         : Robert Huang<56649783@qq.com>
 * @Date           : 2022-05-20 12:50:44
 * @LastEditors    : Robert Huang<56649783@qq.com>
 * @LastEditTime   : 2022-05-28 23:03:16
 * @FilePath       : \web2\src\assets\axiosInterceptorsRequest.js
 * @CopyRight      : Dedienne Aerospace China ZhuHai
 */
import axios from 'axios'
import { SessionStorage } from 'quasar'

axios.interceptors.request.use(
  (config) => {
    // Add xsrf header config.withCredentials
    const xsrfValue = SessionStorage.getItem('XSRF-TOKEN')

    if (xsrfValue) {
      const xsrfConfig = {
        headers: { 'X-XSRF-TOKEN': xsrfValue }
      }
      Object.assign(config, xsrfConfig)
    }

    //console.debug('\u001b[31m' + '[Axios] ', 'Request config:', config)
    return Promise.resolve(config)
  },
  (error) => {
    // It seems never happens, Most error handing please go to response interceptor
    console.debug('\u001b[31m' + '[Axios] ', 'Request error:', error)
    return Promise.reject(error)
  }
)
