package com.example.kuguanli.service.impl

import com.example.kuguanli.dao.InventoryDao
import com.example.kuguanli.service.InventoryInitService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class InventoryInitServiceImpl(
    @Autowired val inventoryDao: InventoryDao

) : InventoryInitService {
    override fun emptyInventory() {
        inventoryDao.deleteAll()
    }

    override fun transferToEnd() {
        TODO("Not yet implemented")
    }

    override fun clearPurchase() {
        TODO("Not yet implemented")
    }

    override fun clearInventoryCheck() {
        TODO("Not yet implemented")
    }

    override fun clearDelivery() {
        TODO("Not yet implemented")
    }

    override fun setBeginningInventory(beginningInventory: Any) {
        TODO("Not yet implemented")
    }
}