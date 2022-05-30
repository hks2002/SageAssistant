/***
 * @Author         : Robert Huang<56649783@qq.com>
 * @Date           : 2022-05-27 09:59:04
 * @LastEditors    : Robert Huang<56649783@qq.com>
 * @LastEditTime   : 2022-05-28 23:09:32
 * @FilePath       : \web2\src\boot\mixin.js
 * @CopyRight      : Dedienne Aerospace China ZhuHai
 */
import lifeCycleDebug from '@/mixins/lifeCycleDebug'
import { boot } from 'quasar/wrappers'

export default boot(({ app } /* { app, router, ... } */) => {
  console.debug('\u001b[35m' + '[Boot] ', 'mixin')

  app.mixin(lifeCycleDebug)
})
