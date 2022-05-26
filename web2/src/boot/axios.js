/**
 * Run Default setting, interceptor
 */
import '@/assets/axiosDefault.js'
import '@/assets/axiosInterceptorsRequest.js'
import '@/assets/axiosInterceptorsResponse.js'
import axios from 'axios'

console.debug('\u001b[35m' + '[Boot] ', 'Axios')

const api = axios.create({ baseURL: 'https://api.example.com' })

export { axios, api }
