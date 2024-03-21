<template>
  <BgContainer>
    <el-table :data="purchaseOrders" border stripe>
      <el-table-column prop="id" label="订单编号"></el-table-column>
      <el-table-column prop="supplierId" label="供应商编号"></el-table-column>
      <el-table-column prop="orderDate" label="下单日期"></el-table-column>
      <el-table-column prop="expectedDeliveryDate" label="预计交货日期"></el-table-column>
      <el-table-column prop="status" label="状态"></el-table-column>
      <el-table-column prop="remark" label="备注"></el-table-column>
      <el-table-column label="操作">
        <template #default="{ row }">
          <el-button size="small" type="primary" @click="handleCheck(row)">验货</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-dialog title="验货" v-model="dialogVisible" width="30%" :before-close="handleDialogClose">
      <el-form :model="checkItem" ref="checkForm" label-width="80px">
        <el-form-item label="验货结果" required>
          <el-radio-group v-model="checkItem.checkResult">
            <el-radio :label="true">合格</el-radio>
            <el-radio :label="false">不合格</el-radio>
          </el-radio-group>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="handleConfirmCheck">确定</el-button>
      </div>
    </el-dialog>
  </BgContainer>
</template>
<script>
import BgContainer from './common/BgContainer.vue';
import { getPurchaseOrdersByStatus, updatePurchaseOrderStatus } from '@/http/api/purchaseOrder.js'

export default {
  components: {
    BgContainer
  },
  data() {
    return {
      purchaseOrders: [], // 所有订单
      dialogVisible: false, // 验货对话框是否可见
      checkItem: {}, // 当前需要验货的订单
    };
  },
  methods: {
    // 点击验货按钮时打开对话框，传递当前订单信息给表单
    handleCheck(row) {
      this.dialogVisible = true;
      this.checkItem = { ...row };
    },
    // 验货对话框关闭前重置表单
    handleDialogClose(done) {
      this.$refs.checkForm.resetFields();
      done();
    },
    // 确认验货时，向服务器发送请求更新订单状态
    handleConfirmCheck() {
      const { id, checkResult } = this.checkItem;
      console.log(id)
      updatePurchaseOrderStatus(id, { status: checkResult ? 1 : 2, }).then(() => {
        this.dialogVisible = false
        const idx = this.purchaseOrders.findIndex(item => item.id === id);
        if (idx !== -1) {
          this.purchaseOrders.splice(idx, 1);
        }
      }).catch(() => {
        this.$message.error('验货失败，请重试');

      });
    },
  },
  created() {
    getPurchaseOrdersByStatus({ status: 0 }).then(res => this.purchaseOrders = res.data.data)

  }
};
</script>
  
<style scoped>
.container {
  margin: 20px;
}
</style>

<style scoped>
.container {
  margin: 20px;
}
</style>