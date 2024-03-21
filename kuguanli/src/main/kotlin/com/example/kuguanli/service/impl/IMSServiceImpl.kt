package com.example.kuguanli.service.impl

import cn.dev33.satoken.stp.StpUtil
import cn.dev33.satoken.util.SaResult
import com.example.kuguanli.dao.GRNDao
import com.example.kuguanli.dao.InventoryDao
import com.example.kuguanli.dao.ProductsInGRNDao
import com.example.kuguanli.dao.PurchaseOrderDao
import com.example.kuguanli.dto.ReceiptDto
import com.example.kuguanli.entity.DeliveryNote
import com.example.kuguanli.entity.PurchaseOrder
import com.example.kuguanli.service.InventoryManagementService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class IMSServiceImpl(
    @Autowired val grnDao: GRNDao,
    @Autowired val itemDao: ProductsInGRNDao,
    @Autowired val purchaseOrderDao: PurchaseOrderDao,
    @Autowired val inventoryDao: InventoryDao
) : InventoryManagementService {
    @Transactional
    override fun addGRN(receiptDto: ReceiptDto): SaResult {
        receiptDto.userUUID= StpUtil.getLoginId() as String

        grnDao.insert(receiptDto).apply {
            return if (receiptDto.grnId != 0) {
                val items=purchaseOrderDao.selectDetailsByPurchaseOrderId(receiptDto.purchaseOrderId)
                purchaseOrderDao.updateStatusById(receiptDto.purchaseOrderId,PurchaseOrder.Status.FINISHED.value)
                items.forEach { item ->
                    itemDao.insert(receiptDto.grnId, item,receiptDto.warehouseId)
                    inventoryDao.updateInventory(item.productId, receiptDto.warehouseId, item.count)
                }
                SaResult.ok("添加进货单成功")
            } else SaResult.error("插入失败")
        }
    }

    override fun addDN(deliveryNote: DeliveryNote) {
        TODO("Not yet implemented")
    }

}