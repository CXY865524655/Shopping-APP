import axios from 'axios'
const instance = axios.create({
  baseURL: '/api', // api的base_url    /api 开发    http://47.112.190.111:8093  App
  timeout: 1000000 // 请求超时时间
})
// request拦截器
instance.interceptors.request.use(
  config => {
    
    return config
  }
)
// respone拦截器
instance.interceptors.response.use(
  res => {
    
    return res
  },
  err => {
    
  }
)
export default instance