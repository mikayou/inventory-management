package com.example.kuguanli.controller

import cn.dev33.satoken.annotation.SaCheckLogin
import cn.dev33.satoken.util.SaResult
import com.example.kuguanli.dao.CustomerDao
import com.example.kuguanli.entity.Customer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/customer")
@SaCheckLogin
class CustomerController(
    @Autowired val customerDao: CustomerDao
) {
    @PostMapping
    fun addCustomer(@RequestBody customer: Customer):SaResult{
        return if (customerDao.insert(customer)>0) SaResult.ok("创建客户成功") else SaResult.error("创建客户失败")
    }

}