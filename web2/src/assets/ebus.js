/***
 * @Author         : Robert Huang<56649783@qq.com>
 * @Date           : 2022-03-25 11:01:23
 * @LastEditors    : Robert Huang<56649783@qq.com>
 * @LastEditTime   : 2022-05-28 23:03:38
 * @FilePath       : \web2\src\assets\ebus.js
 * @CopyRight      : Dedienne Aerospace China ZhuHai
 */
import mitt from 'mitt'

console.debug('\u001b[35m' + '[Load] ', 'Ebus')

const ebus = mitt()

// using it: import {ebus} from '@/assets/ebus'
export { ebus }
