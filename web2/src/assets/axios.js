/***
 * @Author         : Robert Huang<56649783@qq.com>
 * @Date           : 2022-03-25 11:01:23
 * @LastEditors    : Robert Huang<56649783@qq.com>
 * @LastEditTime   : 2022-05-28 23:03:00
 * @FilePath       : \web2\src\assets\axios.js
 * @CopyRight      : Dedienne Aerospace China ZhuHai
 */
/**
 * Run Default setting, interceptor
 */
import '@/assets/axiosDefault.js'
import '@/assets/axiosInterceptorsRequest.js'
import '@/assets/axiosInterceptorsResponse.js'
import axios from 'axios'

console.debug('\u001b[35m' + '[Load] ', 'Axios')

const api = axios.create({ baseURL: 'https://api.example.com' })

export { axios, api }
