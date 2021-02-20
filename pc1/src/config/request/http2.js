import axios from 'axios'

const instance = axios.create({
  baseURL: '/api', // api的base_url    /api 开发    http://47.112.190.111:8093  App
  timeout: 1000000 // 请求超时时间
})

export default instance
