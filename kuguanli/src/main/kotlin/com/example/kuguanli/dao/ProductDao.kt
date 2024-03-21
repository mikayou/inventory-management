package com.example.kuguanli.dao

import com.example.kuguanli.entity.Product
import com.example.kuguanli.vo.ProductVo
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Select
import org.springframework.stereotype.Repository

@Repository
interface ProductDao {

    @Select("select id,name from  products")
    fun selectAll():List<ProductVo>
    @Insert("insert into products(name,catalog_id) values(#{name},#{catalogId})")
    fun insert(product: Product): Int
}