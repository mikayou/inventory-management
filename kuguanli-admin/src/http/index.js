import axios from 'axios'
import router from '@/router';
import store from '../store'

import { ElMessage } from 'element-plus'
const instance = axios.create({
    baseURL: 'http://localhost:8081/kuguanli/api/v1',
    timeout: 1000,
    headers: {
        "content-type": "application/json;charset=UTF-8",
    }
})
instance.interceptors.request.use(
    config => {
        // 获取本地缓存中的 token
        const token = store.state.token
        if (token) {
            console.log(token)
            config.headers['token'] = token
        }
        return config
    },
    error => {
        return Promise.reject(error)
    }
)
// 添加响应拦截器
instance.interceptors.response.use(
    (response) => {
        if (response.data.code == 200 && response.data != '' && response.data.msg != 'ok') {

            ElMessage.success(response.data.msg)
        }
        return response
    },
    (error) => {
        if (error.response && error.response.status === 401) {
            // 如果响应状态码为 401，说明用户身份已过期，跳转到登录页面
            localStorage.removeItem('token');
            router.push({ name: 'Login', replace: true })
        }
        return Promise.reject(error)
    }
);

export default instance