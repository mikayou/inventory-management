package com.example.kuguanli.entity

import cn.dev33.satoken.stp.StpUtil
import java.time.LocalDate

data class GoodsReceiptNote(
    val id: Int = 0,
    val type: Type,
    val date: LocalDate, // 入库日期
    val items: List<Item>, // 物品列表
    val userUUID: String,
    val remark: String?, // 备注，可为空
    val warehouseId: Int
) {
    enum class Type(val value: Int) {
        PURCHASE(0), PRODUCE(1)
    }

}