package com.example.kuguanli.dao

import com.example.kuguanli.entity.User
import org.apache.ibatis.annotations.Select
import org.springframework.stereotype.Repository

@Repository
interface UserDao {
    @Select("select * from users where name=#{name}")
    fun getUserByName(name: String): User?
}