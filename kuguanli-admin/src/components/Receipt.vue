<template>
    <div class="container">
        <div class="header">
            <h2>采购入库</h2>
        </div>
        <el-card>
            <h3>采购订单列表</h3>
            <el-table :data="purchaseOrders" border stripe>
                <el-table-column prop="id" label="采购订单编号"></el-table-column>
                <el-table-column prop="supplierId" label="供应商id"></el-table-column>
                <el-table-column prop="supplierName" label="供应商名"></el-table-column>
                <el-table-column prop="orderDate" label="下单时间"></el-table-column>
                <el-table-column prop="status" label="状态"></el-table-column>
                <el-table-column label="操作">
                    <template #default="{ row }">
                        <el-button size="small" type="primary" @click="selectPurchaseOrder(row)">选择</el-button>
                    </template>
                </el-table-column>
            </el-table>
        </el-card>
        <el-dialog v-model="dialogVisible" width="50%" align-center>
            <h3>采购订单明细</h3>
            <el-form :model="selectedPurchaseOrder" ref="form" label-width="120px" label-position="top">
                <el-form-item label="订单编号">
                    <el-input v-model="selectedPurchaseOrder.id" readonly></el-input>
                </el-form-item>
                <el-form-item label="供应商名">
                    <el-input v-model="selectedPurchaseOrder.supplierName" readonly></el-input>
                </el-form-item>
                <el-form-item label="状态">
                    <el-input v-model="selectedPurchaseOrder.status" readonly></el-input>
                </el-form-item>
                <el-form-item label="选择仓库">
                    <DataSelector v-model:selected="selectedWarehouseId" :data="warehouseData" placeholder="请选择或搜索仓库">
                    </DataSelector>
                </el-form-item>
                <el-form-item label="备注">
                    <el-input v-model="remark" :max="255"></el-input>
                </el-form-item>
                <el-form-item label="货物列表">
                    <el-table :data="selectedPurchaseOrder.items" border stripe max-height="400">
                        <el-table-column prop="productId" label="货物编号"></el-table-column>
                        <el-table-column prop="productName" label="货物名称"></el-table-column>
                        <el-table-column prop="count" label="数量"></el-table-column>
                        <el-table-column prop="unitPrice" label="单价"></el-table-column>
                        <el-table-column prop="subtotal" label="小计">
                            <template #default="{ row }">{{ (row.count * row.unitPrice).toFixed(2) }}</template>
                        </el-table-column>
                    </el-table>
                </el-form-item>
            </el-form>
            <template #footer>
                <span class="dialog-footer">
                    <el-button @click="dialogVisible = false">取消</el-button>
                    <el-button type="primary" @click="handleReceiptSubmit(selectedPurchaseOrder.id)">
                        确认入库
                    </el-button>
                </span>
            </template>
        </el-dialog>
    </div>
</template>
  
<script>
import BgContainer from './common/BgContainer.vue';
import DataSelector from './selector/DataSelector.vue'
import { getPurchaseOrdersByStatus } from '@/http/api/purchaseOrder.js'
import { getAllWarehouses } from '@/http/api/warehouse.js'
import { receving } from '@/http/api/rms.js'
export default {
    components: {
        BgContainer,
        DataSelector
    },
    data() {
        return {
            dialogVisible: false,
            purchaseOrders: [],
            warehouseData: [],
            selectedPurchaseOrder: {},
            selectedWarehouseId: 0,
            remark: '',

        }
    },
    methods: {
        selectPurchaseOrder(row) {
            this.dialogVisible = true
            this.selectedPurchaseOrder = row
        },
        handleReceiptSubmit(value) {
            receving({
                purchaseOrderId: value,
                warehouseId: this.selectedWarehouseId,
                remark: this.remark,
                userUUID: '',
                items: [],
                type: 0
            }).then(() => {
                this.dialogVisible = false
                getPurchaseOrdersByStatus({ status: 1 }).then(res => this.purchaseOrders = res.data.data)
            })
        }
    },


    created() {
        getPurchaseOrdersByStatus({ status: 1 }).then(res => this.purchaseOrders = res.data.data)
        getAllWarehouses().then(res => this.warehouseData = res.data.data)
    }
};
</script>
  
<style>
.container {
    margin: 20px;
}

.header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 20px;
}

.footer {
    display: flex;
    justify-content: flex-end;
    margin-top: 20px;
}
</style>
  