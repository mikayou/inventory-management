import { fileURLToPath, URL } from 'node:url'

import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [vue()],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    }
  },
  server:{
    proxy: { 
      // 选项写法
      '/api': {
        target: 'http://localhost:8081', // 所要代理的目标地址
        rewrite: path => path.replace(/^\/api/, ''), 
        changeOrigin: true, 
      }
    }
  }
})
