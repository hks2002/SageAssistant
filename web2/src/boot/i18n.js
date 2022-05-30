/***
 * @Author         : Robert Huang<56649783@qq.com>
 * @Date           : 2022-03-25 11:01:23
 * @LastEditors    : Robert Huang<56649783@qq.com>
 * @LastEditTime   : 2022-05-28 23:09:27
 * @FilePath       : \web2\src\boot\i18n.js
 * @CopyRight      : Dedienne Aerospace China ZhuHai
 */
import { i18n, langIso } from '@/assets/i18n'
import { Quasar } from 'quasar'
import { boot } from 'quasar/wrappers'

export default boot(({ app } /* { app, router, ... } */) => {
  console.debug('\u001b[35m' + '[Boot] ', 'i18n', langIso)
  // Set i18n instance on app
  app.use(i18n)
  // Set Quasar lang pack
  Promise.resolve(require('quasar/lang/' + langIso))
    .then((lang) => {
      Quasar.lang.set(lang.default)
    })
    .catch((err) => {
      // Requested Quasar Language Pack does not exist,
      // let's not break the app, so catching error
      console.debug(err)
    })
})
