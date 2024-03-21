package com.example.kuguanli.controller

import cn.dev33.satoken.annotation.SaCheckLogin
import cn.dev33.satoken.util.SaResult
import com.example.kuguanli.dao.PurchaseOrderDao
import com.example.kuguanli.dao.SupplierDao
import com.example.kuguanli.dto.ReceiptDto
import com.example.kuguanli.vo.PurchaseOrderVo
import com.example.kuguanli.entity.PurchaseOrder
import com.example.kuguanli.service.InventoryManagementService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*

/**RMS:receipt manage system
 *
 */
@RestController
@RequestMapping("/rms")
@SaCheckLogin
class RMSController(
    @Autowired val purchaseOrderDao: PurchaseOrderDao,
    @Autowired val supplierDao: SupplierDao,
    @Autowired val service: InventoryManagementService
) {
    @Transactional
    @PostMapping("/purchase-order")
    fun addPurchaseOrder(@RequestBody purchaseOrder: PurchaseOrder): SaResult {
        return if (purchaseOrderDao.insertPurchaseOrder(purchaseOrder) > 0) {
            purchaseOrder.items.forEach {
                if (purchaseOrderDao.insertItem(purchaseOrder.id, it) <= 0)
                    SaResult.error("新建采购单失败")
            }
            SaResult.ok("新建采购单成功")
        } else SaResult.error("新建采购单失败")
    }


    @GetMapping("/purchase-order")
    fun allPurchaseOrdersByStatus(@RequestParam status:Int):SaResult{
        var result:MutableList<PurchaseOrderVo> = mutableListOf()
        purchaseOrderDao.selectAllPurchaseOrder(status).mapTo(result) { purchaseOrder ->
            PurchaseOrderVo(purchaseOrder.id,purchaseOrder.supplierId,
                supplierName = supplierDao.selectNameById(purchaseOrder.supplierId)
                ,purchaseOrder.orderDate,purchaseOrder.expectedDeliveryDate,
                when (purchaseOrder.status) {
                    PurchaseOrder.Status.TOBE_DELIVERED -> "等待收货"
                    PurchaseOrder.Status.PASS -> "通过验货"
                    PurchaseOrder.Status.FAIL->"单货不符"
                    PurchaseOrder.Status.FINISHED->"订单完成"
                },purchaseOrder.remark,purchaseOrder.items)
        }
        return SaResult.data(result)
    }
    @PatchMapping("/purchase-order/{id}")
    fun updateStatusByOrderId(@PathVariable("id") id:Int,@RequestParam status:Int):SaResult{
        return if(purchaseOrderDao.updateStatusById(id,status)>0) SaResult.ok("验货成功") else SaResult.error("验货失败，请重新验货")
    }

    @Transactional
    @PostMapping("/receiving")
    fun receiving(@RequestBody receiptDto: ReceiptDto):SaResult{
        return service.addGRN(receiptDto)
    }


}