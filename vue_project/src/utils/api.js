import axios from 'axios'
import {Message, MessageBox} from 'element-ui'
import store from '../store'

axios.defaults.headers['Content-Type']="application/json"
axios.defaults.headers['Authorization']="Basic aW90c3RwOnphcTFYU1cy"
// 创建axios实例
const service = axios.create({
  baseURL: process.env.BASE_URL, // api的base_url
  timeout: 25000                  // 请求超时时间2
})
// request请求拦截器
// const TOKEN = localStorage.getItem("access_token");
service.interceptors.request.use(config => {
  //将接口返回的token信息配置到接口请求中
  // if(TOKEN){
  //   config.headers.Authorization = `bearer ${TOKEN}`
  // }
  return config
}, error => {
  // Do something with request error
  // console.error(error) // for debug
  Promise.reject(error)
})
// respone拦截器
service.interceptors.response.use(
  response => {
    // console.log(response)
    const res = response.data;
    //比如判断token失效为1000
    if (res.returnCode == '1000') {
      //清空
      // localStorage.setItem('access_token','')
      // //强制跳转
      // window.location.href = '/user/login'
      return res;
    }
    if (res.returnCode == '100') {
      return res.returnData;
    } else if (res.returnCode == "20011") {
      return Promise.reject("未登录")
    } else {
      return Promise.reject(res)
    }
  },
  error => {
    //console.error('err' + error)// for debug
    // Message({
    //   message: error.message,
    //   type: 'error',
    //   duration: 3 * 1000
    // })
    return Promise.reject(error)
  }
)
export default service

