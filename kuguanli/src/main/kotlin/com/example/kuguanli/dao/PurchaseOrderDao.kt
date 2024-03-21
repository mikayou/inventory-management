package com.example.kuguanli.dao

import com.example.kuguanli.entity.Item
import com.example.kuguanli.entity.PurchaseOrder
import org.apache.ibatis.annotations.*

import org.apache.ibatis.type.EnumOrdinalTypeHandler
import org.springframework.stereotype.Repository

@Repository
interface PurchaseOrderDao {
    @Insert("insert into purchase_orders(supplier_id,order_date,expected_delivery_date,remark) " +
            "values(#{supplierId},#{orderDate},#{expectedDeliveryDate},#{remark})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    fun insertPurchaseOrder(purchaseOrder: PurchaseOrder):Int

    @Insert("insert into purchase_order_details(purchase_order_id, product_id, count, unit_price) " +
            "values(#{purchaseOrderId}, #{item.productId}, #{item.count}, #{item.unitPrice})")
    fun insertItem(@Param("purchaseOrderId") orderId: Int, @Param("item") item:Item): Int

    @Select("select purchase_order_details.*,name from purchase_order_details,products where purchase_order_id=#{id}" +
            " and products.id=purchase_order_details.product_id")
    @Results(id="purchaseOrderItemMap", value = [
        Result(property = "productId", column = "product_id"),
        Result(property = "unitPrice", column = "unit_price"),
        Result(property = "productName", column = "name"),
    ])
    fun selectDetailsByPurchaseOrderId(id:Int):List<Item>

    @Select("select * from purchase_orders where status=#{status}")
    @Results(id = "purchaseOrderMap", value = [
        Result(property = "id", column = "id", javaType = java.lang.Integer::class),
        Result(property = "supplierId", column = "supplier_id", javaType = java.lang.Integer::class),
        Result(property = "orderDate", column = "order_date", javaType = java.time.LocalDate::class),
        Result(property = "expectedDeliveryDate", column = "expected_delivery_date", javaType = java.time.LocalDate::class),
        Result(property = "status", column = "status", typeHandler = EnumOrdinalTypeHandler::class, javaType = PurchaseOrder.Status::class),
        Result(property = "remark", column = "remark", javaType = java.lang.String::class ),
        Result(property="items", column = "id", many = Many(select = "selectDetailsByPurchaseOrderId"),
            javaType = java.util.List::class)
    ])
    fun selectAllPurchaseOrder(status:Int):List<PurchaseOrder>

    @Update("update purchase_orders set status=#{status} where id=#{id}")
    fun updateStatusById(@Param("id") id: Int, @Param("status") status: Int):Int
}