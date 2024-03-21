<template>
    <div id="container">
        <el-cascader size="large" :options="options" v-model="selectedOptions" @change="handleChange" placeholder="请选择省/市/区">
        </el-cascader>
    </div>
</template>
  
<script>
import { regionData, codeToText } from 'element-china-area-data'
export default {
    name: '',
    components: {},
    data() {
        return {
            options: regionData,
            selectedOptions: [],
            addtions: [],			//存储地址数据
        }
    },
    methods: {
        handleChange(value) {
            this.addtions.selectedOptions = value
            this.selectedOptions.map(item => this.addtions.push(codeToText[item]))
            this.$emit('selectChange',this.addtions)
        }
    },
}
</script>
  
  
  