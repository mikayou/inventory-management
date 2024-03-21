<template>
    <BgContainer>
        <el-form :model="formData" label-width="100px">
            <!-- 输入仓库名称 -->
            <el-form-item label="仓库名称" prop="name" :rules="[{ required: true, message: '请输入仓库名称', trigger: 'blur' }]">
                <el-input v-model.trim="formData.name" :maxlength="20" show-word-limit clearable></el-input>
            </el-form-item>

            <!-- 选择地址 -->
            <el-form-item label="地址" prop="value"
                :rules="[{ required: true, type: 'Array', min: 3, message: '请选择完整的地址信息', trigger: 'change' }]">
                <AddressSelector @selectChange="handleAddressChange"></AddressSelector>
            </el-form-item>

            <!-- 手动输入详细地址 -->
            <el-form-item label="详细地址" prop="address.detail"
                :rules="[{ required: true, message: '请输入详细地址', trigger: 'blur' }]">
                <el-input v-model.trim="formData.address.detail" :maxlength="50" show-word-limit clearable></el-input>
            </el-form-item>

            <!-- 输入联系电话 -->
            <el-form-item label="联系电话" prop="phoneNumber"
                :rules="[{ required: true, message: '请输入联系电话', trigger: 'blur' }]">
                <el-input v-model.trim="formData.phoneNumber" show-word-limit clearable></el-input>
            </el-form-item>

            <el-form-item>
                <el-button type="primary" @click="submitForm">提交</el-button>
            </el-form-item>
        </el-form>
    </BgContainer>
</template>
  
  
<script>
import { reactive } from 'vue';
import AddressSelector from './common/AddressSelector.vue'
import BgContainer from './common/BgContainer.vue';
import { addWarehouse } from '@/http/api/warehouse'

export default {
    components: {
        AddressSelector,
        BgContainer
    },
    data() {
        return {
            formData: reactive({
                name: '',
                address: {
                    province: '',
                    city: '',
                    district: '',
                    detail: ''
                },
                phoneNumber: ''
            }),
            value: []

        };
    },
    methods: {
        handleAddressChange(value) {
            this.value = value
            this.formData.address.province = value[0];
            this.formData.address.city = value[1];
            this.formData.address.district = value[2];
        },
        submitForm() {
            addWarehouse(this.formData)
        }
    }
};
</script>
  