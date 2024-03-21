package com.example.kuguanli.dao

import com.example.kuguanli.entity.Address
import com.example.kuguanli.entity.Warehouse
import org.apache.ibatis.annotations.*
import org.springframework.stereotype.Repository

@Repository
interface WarehouseDao {

    @Insert(
        "insert into warehouses(name,province,city,district,detail,phone_number) values(#{name},#{address.province}" +
                ",#{address.city},#{address.district},#{address.detail},#{phoneNumber})"
    )
    fun insert(warehouse: Warehouse): Int

    @Results(id = "warehouseAddressMap", value = [
        // 包含了 "province", "city", "district" 和 "detail" 的 Address 对象
        // 将这些字段分别从数据库中的字段映射到 Address 对象的相应属性中
        Result(column = "province", property = "province"),
        Result(column = "city", property = "city"),
        Result(column = "district", property = "district"),
        Result(column = "detail", property = "detail"),
    ])
    @Select("select province,city,district,detail from warehouses where id=#{id}")
    fun warehouseAddressResultMap(id:Int): Address

    @Select("SELECT * FROM warehouses")
    @Results(id = "warehouseMap", value = [
        Result(column = "id", property = "id"),
        Result(column = "name", property = "name"),
        Result(column = "id",property = "address",
            javaType = Address::class,one = One(select = "warehouseAddressResultMap")
        )
    ])
    fun selectAll(): List<Warehouse>



}