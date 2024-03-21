package com.example.kuguanli.entity

import java.time.LocalDate

data class DeliveryNote(
    val id: Int,
    val deliveryDate: LocalDate,
    val warehouseId: Int,
    val items: List<Item>,
    val type: Type,
    val remark: String?
) {

    enum class Type {

    }

}

