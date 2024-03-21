package com.example.kuguanli.entity

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDate

data class PurchaseOrder(
    var id: Int = 0,
    var supplierId: Int,
    @JsonFormat(pattern = "yyyy-MM-dd")
    var orderDate: LocalDate,
    @JsonFormat(pattern = "yyyy-MM-dd")
    var expectedDeliveryDate: LocalDate,
    var status: Status = Status.TOBE_DELIVERED,
    var remark: String?,
    var items: List<Item>
) {
    constructor() : this(0, 0, LocalDate.of(2012,1,1), LocalDate.of(2012,1,1), Status.TOBE_DELIVERED, null, emptyList())
    enum class Status(val value: Int) {
        TOBE_DELIVERED(0), PASS(1), FAIL(2), FINISHED(3)
    }


}