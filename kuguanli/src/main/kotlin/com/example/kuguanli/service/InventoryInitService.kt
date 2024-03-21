package com.example.kuguanli.service

interface InventoryInitService {
    fun emptyInventory()
    fun transferToEnd()
    fun clearPurchase()
    fun clearInventoryCheck()
    fun clearDelivery()
    abstract fun setBeginningInventory(beginningInventory: Any)
}
