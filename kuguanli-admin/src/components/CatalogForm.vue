<template>
    <BgContainer>
        <el-form :rules="rules" :model="form" ref="form" label-width="80px" @submit.native.prevent>
            <!-- 表单项，name -->
            <el-form-item label="类别名称" prop="name">
                <el-input v-model="form.name" placeholder="请输入类别名称"></el-input>
            </el-form-item>

            <!-- 提交按钮 -->
            <el-form-item>
                <el-button type="primary" @click="submitForm">提交</el-button>
            </el-form-item>
        </el-form>
    </BgContainer>
</template>
  
<script>
import { addCatalog } from '@/http/api/catalog.js';
import BgContainer from './common/BgContainer.vue';
export default {
    data() {
        return {
            form: {
                name: ''
            },
            rules: {
                name: [{ required: true, message: "请输入类别名称", trigger: "blur" }],
            },
        };
    },
    methods: {
        submitForm() {
            this.$refs.form.validate((valid) => {
                if (valid) {
                    // 表单验证通过，提交数据
                    addCatalog(this.form)
                }
                else {
                    // 验证未通过，不提交数据
                    return false;
                }
            });
        },
    },
    components: { BgContainer }
};
</script>
  