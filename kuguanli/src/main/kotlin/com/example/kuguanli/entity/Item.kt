package com.example.kuguanli.entity

data class Item(
    val productId: Int, // 物品id
    val productName:String?,
    val count: Int, // 物品数量
    val unitPrice: Double, // 单价
)

