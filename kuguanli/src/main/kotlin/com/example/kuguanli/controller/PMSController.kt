package com.example.kuguanli.controller

import cn.dev33.satoken.annotation.SaCheckLogin
import cn.dev33.satoken.util.SaResult
import com.example.kuguanli.dao.ProductDao
import com.example.kuguanli.entity.Product
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * pms 产品管理
 */
@RestController
@RequestMapping("/product")
@SaCheckLogin
class PMSController(
    @Autowired val productDao: ProductDao
) {
    @PostMapping("")
    fun addProduct(@RequestBody product: Product): SaResult {
        return if (productDao.insert(product) > 0) SaResult.ok("增加商品成功") else SaResult.error("增加商品失败")
    }

    @GetMapping
    fun allProducts(): SaResult {
        return  SaResult.data(productDao.selectAll())
    }
}