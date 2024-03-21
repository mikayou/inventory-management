package com.example.kuguanli.dao

import com.example.kuguanli.entity.Customer
import org.apache.ibatis.annotations.Insert
import org.springframework.stereotype.Repository

@Repository
interface CustomerDao {

    @Insert("insert into customers(name,phone_number) values(#{name},#{phoneNumber})")
    fun insert(customer: Customer):Int
}