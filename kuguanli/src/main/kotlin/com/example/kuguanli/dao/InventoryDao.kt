package com.example.kuguanli.dao

import com.example.kuguanli.vo.InventoryVo
import org.apache.ibatis.annotations.*
import org.springframework.stereotype.Repository

@Repository
interface InventoryDao {
    @Select("select name,product_id,inventory from inventory,products where warehouse_id=#{warehouseId} and inventory.product_id=products.id")
    @Results(
        id = "inventoryMap", value = [
            Result(column = "product_id", property = "productId"),
            Result(column = "name", property = "productName", javaType = java.lang.String::class),
            Result(column = "inventory", property = "inventory")
        ]
    )
    fun getAllInventoryByWarehouseId(warehouseId: Int): List<InventoryVo>

    @Update("CALL increase_inventory(#{productId}, #{warehouseId}, #{count})")
    fun updateInventory(
        @Param("productId") productId: Int,
        @Param("warehouseId") warehouseId: Int,
        @Param("count") count: Int

    )

    @Delete("TRUNCATE TABLE inventory")
    fun deleteAll()
}