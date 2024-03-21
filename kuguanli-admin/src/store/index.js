
import { createStore } from 'vuex'
import instance from '../http'
const store = createStore({
  state: {
    token: localStorage.token
  },
  mutations: {
    setToken(state, token) {
      instance.defaults.headers.token = token
      state.token = token
      // 将 token 存入本地缓存
      localStorage.setItem('token', token)
    },
    clearToken(state) {
      state.token = null
      // 清除本地缓存中的 token
      localStorage.removeItem('token')
    }
  },
  computed: {
    token() {
      return store.state.token
    }
  },
})

export default store
