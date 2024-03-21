package com.example.kuguanli.dao

import com.example.kuguanli.entity.Item
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Param
import org.springframework.stereotype.Repository

@Repository
interface ProductsInGRNDao {

    @Insert("insert into grn_details(grn_id,product_id,count,unit_price,warehouse_id) values(#{grnId},#{item.productId},#{item.count},#{item.unitPrice},#{warehouseId})")
    fun insert(@Param("grnId") grnId: Int, @Param("item") item: Item,@Param("warehouseId") warehouseId: Int)
}