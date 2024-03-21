package com.example.kuguanli.dao

import com.example.kuguanli.dto.ReceiptDto
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Options
import org.springframework.stereotype.Repository

@Repository
interface GRNDao {
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "grnId")
    @Insert("insert into grns(user_uuid,remark,purchase_order_id) values(#{userUUID},#{remark},#{purchaseOrderId})")
    fun insert(receipt: ReceiptDto):Int
}