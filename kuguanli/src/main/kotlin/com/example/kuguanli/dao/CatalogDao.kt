package com.example.kuguanli.dao

import com.example.kuguanli.entity.Catalog
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Select
import org.springframework.stereotype.Repository
import java.util.Arrays

@Repository
interface CatalogDao {

    @Insert("insert into catalogs(name) values(#{name})")
    fun insert(catalog: Catalog): Int

    @Select("select * from catalogs")
    fun getAll():Array<Catalog>

}