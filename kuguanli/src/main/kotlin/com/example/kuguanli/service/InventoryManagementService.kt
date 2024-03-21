package com.example.kuguanli.service

import cn.dev33.satoken.util.SaResult
import com.example.kuguanli.dto.ReceiptDto
import com.example.kuguanli.entity.DeliveryNote

interface InventoryManagementService {

    fun addGRN(receiptDto: ReceiptDto): SaResult
    fun addDN(deliveryNote: DeliveryNote)
}