/***
 * @Author         : Robert Huang<56649783@qq.com>
 * @Date           : 2022-05-20 12:50:44
 * @LastEditors    : Robert Huang<56649783@qq.com>
 * @LastEditTime   : 2022-05-28 23:03:21
 * @FilePath       : \web2\src\assets\axiosInterceptorsResponse.js
 * @CopyRight      : Dedienne Aerospace China ZhuHai
 */
import { i18n } from '@/assets/i18n'
import axios from 'axios'
import { Notify } from 'quasar'

const { t } = i18n.global

axios.interceptors.response.use(
  (response) => {
    console.debug('\u001b[31m' + '[Axios] ', 'Response success:', response)
    return Promise.resolve(response)
  },
  (error) => {
    console.error('\u001b[31m' + '[Axios] ', 'Response error:', error)

    if (error.response) {
      if (error.response.status === 401 || error.response.status === 403) {
        Notify.create({
          type: 'warning',
          message: t('You are not authorized')
        })
      } else if (error.response.status / 100 === 5) {
        Notify.create({
          type: 'negative',
          message: t('Server error')
        })
      }
    } else {
      Notify.create({
        type: 'negative',
        message: t('Contact your admin')
      })
    }

    return Promise.reject(error.response)
  }
)
