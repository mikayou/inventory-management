package com.example.kuguanli.controller

import cn.dev33.satoken.annotation.SaCheckLogin
import cn.dev33.satoken.util.SaResult
import com.example.kuguanli.dao.SupplierDao
import com.example.kuguanli.entity.Supplier
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/supplier")
@SaCheckLogin
class SupplierController(
    @Autowired val supplierDao: SupplierDao
) {

    @GetMapping
    fun allSupplier():SaResult{
        return  SaResult.data(supplierDao.selectAll())
    }
    @PostMapping
    fun addSupplier(@RequestBody supplier: Supplier):SaResult{
        return  if (supplierDao.insert(supplier)>0) SaResult.ok("创建供应商成功") else SaResult.error("失败")
    }
}