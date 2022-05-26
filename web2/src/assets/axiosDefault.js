import axios from 'axios'
axios.defaults.headers.patch['Content-Type'] = 'application/json;charset=utf-8'
axios.defaults.headers.post['Content-Type'] = 'application/json;charset=utf-8'
axios.defaults.headers.put['Content-Type'] = 'application/json;charset=utf-8'
axios.defaults.withCredentials = true // Allow send cookies
axios.defaults.timeout = 20000
