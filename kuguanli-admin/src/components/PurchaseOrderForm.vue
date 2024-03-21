<template>
    <BgContainer>
        <div class="purchase-input-form">
            <el-form ref="orderForm" :model="orderForm" status-icon :rules="rules" label-width="75px" label-position="top">
                <el-form-item label="供应商" prop="supplierId">
                    <DataSelector :data="supplierData" v-model:selected="orderForm.supplierId" placeholder="请选择或输入供应商名称">
                    </DataSelector>
                </el-form-item>
                <el-form-item label="下单日期" prop="orderDate" required>
                    <el-date-picker v-model="orderForm.orderDate" type="date" placeholder="选择下单日期"
                        :default-value="new Date()" />
                </el-form-item>
                <el-form-item label="预计送达日期" prop="exceptedDeliveryDate" required>
                    <el-date-picker v-model="orderForm.exceptedDeliveryDate" type="date" placeholder="选择送达日期"
                        :default-value="new Date()" />
                </el-form-item>
                <el-form-item label="备注" prop="remark">
                    <el-input v-model="orderForm.remark" :max="255" type="">
                    </el-input>
                </el-form-item>
                <el-form-item label="商品明细" prop="supplierId" required>
                    <div class="product-table-wrapper">
                        <div>
                            <el-button type="primary" size="default" @click="tableData.push({})">新增产品</el-button>
                        </div>
                    </div>
                </el-form-item>
                <el-table :data="tableData" style="width: 100%">
                    <el-table-column type="index" width="50" />
                    <el-table-column prop="id" label="商品编号"></el-table-column>
                    <el-table-column label="商品名称" width="100">
                        <template #default="{ row }">
                            <DataSelector :data="productsData" v-model:selected="row.id" placeholder="请选择或输入产品名称">
                            </DataSelector>
                        </template>
                    </el-table-column>
                    <el-table-column prop="unitPrice" label="单价">
                        <template #default="{ row }">
                            <el-input v-model="row.unitPrice" type="number"
                                @blur="row.unitPrice = Number(row.unitPrice).toFixed(2)"></el-input>
                        </template>
                    </el-table-column>
                    <el-table-column prop="quantity" label="数量">
                        <template #default="{ row }">
                            <el-input v-model.number="row.quantity"></el-input>
                        </template>
                    </el-table-column>
                    <el-table-column label="金额" width="100">
                        <template #default="{ row }">
                            <p class="red" v-if="row.quantity && row.unitPrice">￥{{ row.quantity * row.unitPrice }}</p>
                            <p class="red" v-else>￥0</p>
                        </template>
                    </el-table-column>
                    <el-table-column label="操作">
                        <template #default="{ row }">
                            <el-button type="danger" size="default" @click="handleDelete(row)">删除</el-button>
                        </template>
                    </el-table-column>
                </el-table>
                <el-form-item class="padding-top-50px">
                    <el-button type="primary" @click="handleSubmit">提交</el-button>
                    <el-button @click="handleReset">重置</el-button>
                </el-form-item>
            </el-form>
        </div>

    </BgContainer>
</template>

<script>
import { addPurchaseOrder } from '@/http/api/purchaseOrder.js'
import BgContainer from '@/components/common/BgContainer.vue';
import DataSelector from '@/components/selector/DataSelector.vue'
import { getAllProducts } from '@/http/api/product.js'
import { getAllSuppliers } from '@/http/api/supplier.js'
import moment from 'moment';
export default {
    components: {
        BgContainer,
        DataSelector
    },
    data() {
        return {
            supplierData: [],
            productsData: [],
            tableData: [],
            orderForm: {
                supplierId: 0,
                remark: '',
                status:0,
                orderDate: new Date(),
                exceptedDeliveryDate: new Date(),
                items: []
            },
            rules: {
                supplierId: [
                    { required: true, message: '请选择供应商', trigger: 'change' }
                ]
            }

        };
    },
    methods: {
        handleAdd() {
            this.tableData.push({
                name: '',
                quantity: 0,
                unitPrice: 0
            });
        },
        handleDelete(row) {
            const index = this.tableData.indexOf(row);
            if (index !== -1) {
                this.tableData.splice(index, 1);
            }
        },
        handleSubmit() {
            this.$refs.orderForm.validate((valid) => {
                if (valid) {
                    if (this.tableData.length === 0 || !this.tableData.every(item => item.id && item.unitPrice && item.quantity)) {
                        this.$message.error('请填写完整的商品明细');
                    } else if (!this.orderForm.supplierId) {
                        this.$message.error('请选择供应商');
                    } else {
                        this.orderForm.orderDate = moment(this.orderForm.orderDate).format('YYYY-MM-DD');
                        this.orderForm.expectedDeliveryDate = moment(this.orderForm.expectedDeliveryDate).format('YYYY-MM-DD');

                        // 将tableData转换为items数组
                        this.orderForm.items = this.tableData.map(item => ({
                            productId: item.id,
                            count: item.quantity,
                            unitPrice: parseFloat(item.unitPrice)
                        }));
                        console.log(this.orderForm)
                        addPurchaseOrder(this.orderForm)
                    }
                } else {
                    return false;
                }
            });
        },

        handleReset() {
            this.$refs.orderForm.resetFields();
            this.tableData = [];
        },
        handleDelete(row) {
            const index = this.tableData.indexOf(row);
            if (index !== -1) {
                this.tableData.splice(index, 1);
            }
        },
    },
    created() {
        getAllProducts().then(res => this.productsData = res.data.data)
        getAllSuppliers().then(res => this.supplierData = res.data.data)
    }
};
</script>

<style>
.purchase-input-form {
    max-width: 600px;
    margin: 20px auto;
}

.product-table-wrapper {
    min-width: 400px;
}

.el-input__inner[type="number"]::-webkit-outer-spin-button,
.el-input__inner[type="number"]::-webkit-inner-spin-button {
    display: none !important;
}

.padding-top-50px {
    padding-top: 50px;
}

.red {
    color: #CF352E;
}
</style>
