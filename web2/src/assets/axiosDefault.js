/***
 * @Author         : Robert Huang<56649783@qq.com>
 * @Date           : 2022-05-20 12:50:44
 * @LastEditors    : Robert Huang<56649783@qq.com>
 * @LastEditTime   : 2022-05-28 23:03:12
 * @FilePath       : \web2\src\assets\axiosDefault.js
 * @CopyRight      : Dedienne Aerospace China ZhuHai
 */
import axios from 'axios'
axios.defaults.headers.patch['Content-Type'] = 'application/json;charset=utf-8'
axios.defaults.headers.post['Content-Type'] = 'application/json;charset=utf-8'
axios.defaults.headers.put['Content-Type'] = 'application/json;charset=utf-8'
axios.defaults.withCredentials = true // Allow send cookies
axios.defaults.timeout = 20000
