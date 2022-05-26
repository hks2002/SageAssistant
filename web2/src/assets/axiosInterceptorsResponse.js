import { i18n } from '@/boot/i18n'
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
