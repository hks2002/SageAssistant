/***
 * @Author         : Robert Huang<56649783@qq.com>
 * @Date           : 2022-05-26 14:52:28
 * @LastEditors    : Robert Huang<56649783@qq.com>
 * @LastEditTime   : 2022-05-28 23:11:56
 * @FilePath       : \web2\src\stores\index.js
 * @CopyRight      : Dedienne Aerospace China ZhuHai
 */
import { createPinia } from 'pinia'
import { store } from 'quasar/wrappers'

/*
 * If not building with SSR mode, you can
 * directly export the Store instantiation;
 *
 * The function below can be async too; either use
 * async/await or return a Promise which resolves
 * with the Store instance.
 */

export default store((/* { ssrContext } */) => {
  const pinia = createPinia()

  // You can add Pinia plugins here
  // pinia.use(SomePiniaPlugin)

  return pinia
})
