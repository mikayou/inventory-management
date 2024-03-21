package com.example.kuguanli.controller

import cn.dev33.satoken.annotation.SaCheckLogin
import cn.dev33.satoken.util.SaResult
import com.example.kuguanli.dao.CatalogDao
import com.example.kuguanli.entity.Catalog
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/catalog")
@SaCheckLogin
class CatalogController(
    @Autowired val catalogDao: CatalogDao
) {
    @PostMapping
    fun addCatalog(@RequestBody catalog: Catalog): SaResult {
        return if (catalogDao.insert(catalog) > 0) SaResult.ok("增加类别成功") else SaResult.error("插入错误")
    }

    @GetMapping("")
    fun allCatalogs(): SaResult {
        return SaResult.data(catalogDao.getAll())
    }
}