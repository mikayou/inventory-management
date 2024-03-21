package com.example.kuguanli.vo

import com.example.kuguanli.entity.Item
import com.example.kuguanli.entity.PurchaseOrder
import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDate

data class PurchaseOrderVo(
    var id: Int = 0,
    var supplierId: Int,
    var supplierName: String?,
    @JsonFormat(pattern = "yyyy-MM-dd")
    var orderDate: LocalDate,
    @JsonFormat(pattern = "yyyy-MM-dd")
    var expectedDeliveryDate: LocalDate,
    var status: String,
    var remark: String?,
    var items: List<Item>
)
