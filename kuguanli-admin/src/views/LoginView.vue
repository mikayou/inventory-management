<template>
    <el-card class="login-card">
        <h2 class="login-title">欢迎登录库存管理系统</h2>
        <el-form ref="loginForm" :model="loginForm" :rules="loginRules" label-width="80px" class="login-form">
            <el-form-item label="用户名" prop="name">
                <el-input v-model="loginForm.name"></el-input>
            </el-form-item>
            <el-form-item label="密码" prop="password">
                <el-input type="password" v-model="loginForm.password"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="handleSubmit">登录</el-button>
            </el-form-item>
        </el-form>
    </el-card>
</template>
  
<script>
import { login } from '@/http/api/user.js'
export default {
    data() {
        return {
            loginForm: {
                name: 'admin',
                password: '123456'
            },
            loginRules: {
                name: [
                    { required: true, message: '请输入用户名', trigger: 'blur' }
                ],
                password: [
                    { required: true, message: '请输入密码', trigger: 'blur' }
                ]
            }
        }
    },
    methods: {
        handleSubmit() {
            login(this.loginForm).then(
                response => {
                    if (response.data.code == 200) {
                        this.$store.commit('setToken', response.data.data.tokenValue )
                        this.$message({
                            message: '登录成功',
                            type: 'success',
                            duration: 1500,
                            center: true
                        })
                        this.$router.push({ name: 'Main' })
                    } else if (response.data.code == 500) {
                        this.$message({
                            message: '密码错误或用户名不存在',
                            type: 'error'
                        })
                    }
                }
            )
        }
    }
}
</script>
  
<style scoped>
.login-card {
    width: 400px;
    margin: 100px auto;
    background-color: #f0f2f5;
    border-radius: 10px;
    padding: 30px;
}

.login-title {
    text-align: center;
    margin-bottom: 20px;
}

.login-form {
    margin-top: 30px;
    margin: 0 auto;
    text-align: center;
}
</style>
  