package com.example.kuguanli.controller

import cn.dev33.satoken.annotation.SaCheckLogin
import cn.dev33.satoken.util.SaResult
import com.example.kuguanli.dao.InventoryDao
import com.example.kuguanli.dto.ReceiptDto
import com.example.kuguanli.entity.DeliveryNote
import com.example.kuguanli.entity.Warehouse
import com.example.kuguanli.service.InventoryManagementService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/inventory")
@SaCheckLogin
class InventoryManageController(
    @Autowired val inventoryDao: InventoryDao,
    @Autowired val service: InventoryManagementService
) {

    //delivery notes 出货单
    @PostMapping("/dn")
    fun addDN(@RequestBody deliveryNote: DeliveryNote) {
        return service.addDN(deliveryNote)
    }

    @GetMapping()
    fun addDN(@RequestParam("warehouse_id") warehouseId: Int):SaResult{
       return SaResult.data(inventoryDao.getAllInventoryByWarehouseId(warehouseId))
    }


}