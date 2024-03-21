<template>
    <BgContainer>
        <el-form :model="form" ref="form" :rules="rules" label-width="80px" :inline="false" size="normal">
            <el-form-item label="选择仓库">
                <DataSelector v-model:selected="selectedWarehouseId" :data="warehouseData" placeholder="请选择或搜索仓库">
                </DataSelector>
            </el-form-item>
            <!-- 查看库存 -->
            <el-table :data="inventory" style="margin-top: 20px">
                <el-table-column prop="productId" label="商品id"></el-table-column>
                <el-table-column prop="productName" label="商品名称"></el-table-column>
                <el-table-column prop="inventory" label="库存数量"></el-table-column>
            </el-table>
        </el-form>
    </BgContainer>
</template>
  
<script>

import BgContainer from './common/BgContainer.vue';
import { getAllWarehouses } from '@/http/api/warehouse.js'
import { getAllInventoryOfWarehouse } from '../http/api/inventory.js'
import DataSelector from './selector/DataSelector.vue';
export default {

    components: {
        BgContainer,
        DataSelector
    },    // 组件数据
    data() {
        return {
            selectedWarehouseId: 0,
            warehouseData: [],
            inventory: []

        };
    },
    watch: {
        selectedWarehouseId(value) {
            getAllInventoryOfWarehouse({ warehouse_id: value }).then(res => this.inventory = res.data.data)
        }

    },
    // 计算属性，根据选择的仓库来过滤库存记录
    computed: {
        filteredInventory() {
            if (!this.selectedWarehouse) {
                return [];
            }
            return this.inventory.filter((item) => {
                return item.warehouseId === this.selectedWarehouse;
            });
        },
    },
    created() {
        getAllWarehouses().then(res => {
            this.warehouseData = res.data.data
            this.selectedWarehouseId = this.warehouseData[0].warehouse_id
        })

    }
}

</script>
  