/***
 * @Author         : Robert Huang<56649783@qq.com>
 * @Date           : 2022-03-25 11:01:23
 * @LastEditors    : Robert Huang<56649783@qq.com>
 * @LastEditTime   : 2022-05-28 23:09:37
 * @FilePath       : \web2\src\boot\mock.js
 * @CopyRight      : Dedienne Aerospace China ZhuHai
 */
// the mock index only works in DEV
if (process.env.DEV) {
  console.debug('\u001b[35m' + '[Boot] ', 'Mock')
  require('@/mock/index')
}
