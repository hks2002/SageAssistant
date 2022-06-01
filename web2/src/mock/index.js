/***
 * @Author         : Robert Huang<56649783@qq.com>
 * @Date           : 2022-03-25 11:01:23
 * @LastEditors    : Robert Huang<56649783@qq.com>
 * @LastEditTime   : 2022-05-28 23:13:07
 * @FilePath       : \web2\src\mock\index.js
 * @CopyRight      : Dedienne Aerospace China ZhuHai
 */
// When in development mode, Run Mock
// This file is designed to for boot/mock.js, boot once
// Auto import all files under services folder
if (process.env.DEV) {
  console.debug('\u001b[35m' + '[Mock] ', 'Mounting')

  // auto require all files in /src/mock/services
  const mockfiles = require.context('/src/mock/services', false, /.js$/)
  mockfiles.keys().forEach((key) => {
    const file = key.slice(2)
    require(`/src/mock/services/${file}`)
  })
  console.debug('\u001b[35m' + '[Mock] ', 'Mounted')
}
