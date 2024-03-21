package com.example.kuguanli.controller

import cn.dev33.satoken.annotation.SaCheckLogin
import com.example.kuguanli.entity.Item
import com.example.kuguanli.service.InventoryInitService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.condition.ConditionMessage.ItemsBuilder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/inventory/init")
@SaCheckLogin
class InventoryInitController(
   @Autowired val inventoryInitService: InventoryInitService
) {

    @PostMapping("/empty-inventory")
    fun emptyInventory() {
        inventoryInitService.emptyInventory()
    }

    @PostMapping("/transfer-to-end")
    fun transferToEnd() {
        inventoryInitService.transferToEnd()
    }

    @PostMapping("/clear-purchase")
    fun clearPurchase() {
        inventoryInitService.clearPurchase()
    }

    @PostMapping("/clear-delivery")
    fun clearDelivery() {
        inventoryInitService.clearDelivery()
    }

    @PostMapping("/clear-inventory-check")
    fun clearInventoryCheck() {
        inventoryInitService.clearInventoryCheck()
    }

    @PostMapping("/set-beginning-inventory")
    fun setBeginningInventory(@RequestBody items:List<Item>) {
        inventoryInitService.setBeginningInventory(items)
    }
}
