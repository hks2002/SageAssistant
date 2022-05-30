/***
 * @Author         : Robert Huang<56649783@qq.com>
 * @Date           : 2022-03-25 11:01:23
 * @LastEditors    : Robert Huang<56649783@qq.com>
 * @LastEditTime   : 2022-05-28 23:11:39
 * @FilePath       : \web2\src\router\index.js
 * @CopyRight      : Dedienne Aerospace China ZhuHai
 */
import { getToken } from '@/assets/storage'
import { usePagesStore } from '@/stores/pageManager'
import { route } from 'quasar/wrappers'
import {
  createMemoryHistory,
  createRouter,
  createWebHashHistory,
  createWebHistory
} from 'vue-router'
import routes from './routes'

/*
 * If not building with SSR mode, you can
 * directly export the Router instantiation;
 *
 * The function below can be async too; either use
 * async/await or return a Promise which resolves
 * with the Router instance.
 */

export default route(function (/* { store, ssrContext } */) {
  const createHistory = process.env.SERVER
    ? createMemoryHistory
    : process.env.VUE_ROUTER_MODE === 'history'
    ? createWebHistory
    : createWebHashHistory

  const Router = createRouter({
    scrollBehavior: () => ({ left: 0, top: 0 }),
    routes,

    // Leave this as is and make changes in quasar.conf.js instead!
    // quasar.conf.js -> build -> vueRouterMode
    // quasar.conf.js -> build -> publicPath
    history: createHistory(
      process.env.MODE === 'ssr' ? void 0 : process.env.VUE_ROUTER_BASE
    )
  })

  const pagesStore = usePagesStore()

  Router.beforeEach((to, from, next) => {
    // if not get login token, redirect to login
    if (!process.env.DEV && !getToken()) {
      next('/Login')
      return
    }

    // these pages skip pagesManage
    if (
      to.path === '/Login' ||
      to.path === '/WaitInput ' ||
      to.path.startsWith('/Report/')
    ) {
      next()
      return
    }

    // forward, backward doesn't have name, get name from matched array
    const idx = to.matched.findIndex((r) => r.name === to.name)

    if (idx >= 0) {
      const name = to.matched[idx].name || to.matched[0].name

      const query = to.query
      const params = to.params

      const queryStr = JSON.stringify(query).slice(1, -1)
      const paramsStr = JSON.stringify(params).slice(1, -1)
      const nameWithParams = name + queryStr + paramsStr

      pagesStore.hasPage(nameWithParams)
        ? pagesStore.setActivePage(nameWithParams)
        : pagesStore.addPage({
            id: nameWithParams,
            name: name,
            query: query,
            params: params
          })
      // default
      next()
    } else {
      console.error('Router Error:', to)
    }
  })

  return Router
})
