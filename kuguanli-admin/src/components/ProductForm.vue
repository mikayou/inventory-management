<template>
    <BgContainer>
        <el-form ref="form" :model="form" :rules="rules" label-width="120px">
            <el-form-item label="产品名称" prop="name">
                <el-input v-model="form.name" class="input"></el-input>
            </el-form-item>
            <el-form-item label="产品类别" prop="catalogId">
                <el-select v-model="form.catalogId" filterable class="input" placeholder="请选择一个/输入搜索">
                    <el-option v-for="catalog in catalogs" :key="catalog.id" :label="catalog.name"
                        :value="catalog.id"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="submitForm">提交</el-button>
                <el-button @click="resetForm">重置</el-button>
            </el-form-item>
        </el-form>
    </BgContainer>
</template>
  
<style scoped>
.input {
    width: 300px;
}
</style>
  
<script>
import BgContainer from '@/components/common/BgContainer.vue';
import { getAllCatalogs } from '@/http/api/catalog.js';
import { addProduct} from '@/http/api/product.js'
export default {
    components: {
        BgContainer,
    },
    data() {
        return {
            form: {
                name: '',
                catalogId: '',
            },
            catalogs: [],
            rules: {
                name: [{ required: true, message: '请输入产品名称', trigger: 'blur' }],
                catalogId: [{ required: true, message: '请选择产品类别', trigger: 'change' }],
            },
        };
    },
    created() {
        getAllCatalogs().then(response => {
            this.catalogs = response.data.data;
        });
    },
    methods: {
        submitForm() {
            this.$refs.form.validate(valid => {
                if (valid) {
                    addProduct(this.form)
                } else {
                    return false
                }
            });
        },
        resetForm() {
            this.$refs.form.resetFields();
        },
    },
};
</script>
  