<template>
    <BgContainer>
        <el-form :rules="rules" :model="form" ref="form" label-width="80px">
            <!-- 表单项，name -->
            <el-form-item label="姓名" prop="name">
                <el-input v-model="form.name" placeholder="请输入姓名"></el-input>
            </el-form-item>

            <!-- 表单项，phoneNumber -->
            <el-form-item label="手机号" prop="phoneNumber">
                <el-input v-model="form.phoneNumber" placeholder="请输入手机号"></el-input>
            </el-form-item>

            <!-- 提交按钮 -->
            <el-form-item>
                <el-button type="primary" @click="submitForm">提交</el-button>
            </el-form-item>
        </el-form>
    </BgContainer>
</template>
  
<script>
import { addCustomer } from '@/http/api/customer.js'
import BgContainer from '@/components/common/BgContainer.vue';
import { addSupplier } from '@/http/api/supplier.js'
export default {
    components: {
        BgContainer
    },
    data() {
        return {
            form: {
                name: '',
                phoneNumber: ''
            },

            rules: {
                name: [{ required: true, message: '请输入姓名', trigger: 'blur' }],
                phoneNumber: [
                    { required: true, message: '请输入手机号', trigger: 'blur' },
                    {
                        pattern: /^1[34578]\d{9}$/,
                        message: '手机号格式不正确',
                        trigger: 'blur',
                    },
                ],
            },
        };
    },
    methods: {
        submitForm() {
            this.$refs.form.validate((valid) => {
                if (valid) {
                    // 表单验证通过，提交数据
                    if (this.$route.path.includes('supplier')) {
                        addSupplier(this.form)
                    } else if (this.$route.path.includes('customer')) {
                        addCustomer(this.form)
                    }

                } else {
                    // 验证未通过，不提交数据
                    return false;
                }
            });
        },

    },
};
</script>
  