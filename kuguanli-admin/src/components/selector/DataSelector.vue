<template>
    <div>
        <el-select v-model="value" filterable reserve-keyword :placeholder="placeholder">
            <el-option v-for="item in data" :key="item.id" :label="item.name" :value="item.id"></el-option>
        </el-select>
    </div>
</template>
  
<script>
export default {
    emits: ['update:selected'],
    props: {
        selected: {
            type: Number,
            required: true
        },
        data: {
            type: Array,
            default: []
        },
        placeholder: {
            type: String,
            required: true
        }
    },
    data() {
        return {
            value: ''
        }
    },
    computed: {
        value: {
            get() {
                return this.value
            },
            set(value) {
                this.value=value
                this.$emit('update:selected', value)
            }
        }
    }
}
</script>
  