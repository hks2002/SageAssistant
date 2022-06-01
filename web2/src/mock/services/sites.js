/***
 * @Author         : Robert Huang<56649783@qq.com>
 * @Date           : 2022-03-25 11:01:23
 * @LastEditors    : Robert Huang<56649783@qq.com>
 * @LastEditTime   : 2022-05-28 23:16:19
 * @FilePath       : \web2\src\mock\services\sites.js
 * @CopyRight      : Dedienne Aerospace China ZhuHai
 */
import Mock from 'mockjs'

Mock.mock(/^\/Data\/Sites$/, () => {
  console.debug('\u001b[35m' + '[Mocking] ', 'Sites')
  return ['ZHU', 'HKG', 'SGP', 'TLS', 'MIA', 'AAA']
})
