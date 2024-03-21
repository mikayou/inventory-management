package com.example.kuguanli.dao

import com.example.kuguanli.entity.Supplier
import com.example.kuguanli.vo.SupplierVo
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Select
import org.springframework.stereotype.Repository

@Repository
interface SupplierDao {
    @Select("select id,name from  suppliers")
    fun selectAll(): List<SupplierVo>

    @Select("select name from suppliers where id=#{id}")
    fun selectNameById(id: Int): String

    @Insert("insert into suppliers(name,phone_number) values(#{name},#{phoneNumber})")
    fun insert(supplier: Supplier): Int
}