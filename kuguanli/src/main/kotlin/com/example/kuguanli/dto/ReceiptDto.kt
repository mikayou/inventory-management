package com.example.kuguanli.dto

import cn.dev33.satoken.stp.StpUtil
import com.example.kuguanli.entity.Item


data class ReceiptDto(
    var grnId: Int = 0,
    val type: Int=0,
    val purchaseOrderId: Int,
    var userUUID: String?="",
    val remark: String,
    val warehouseId: Int,
    var items: List<Item> =emptyList()
)
