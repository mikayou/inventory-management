package com.example.kuguanli.controller

import cn.dev33.satoken.annotation.SaCheckLogin
import cn.dev33.satoken.util.SaResult
import com.example.kuguanli.dao.WarehouseDao
import com.example.kuguanli.entity.Warehouse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/warehouse")
@SaCheckLogin
class WarehouseController(
    @Autowired val warehouseDao: WarehouseDao
) {

    @PostMapping("")
    fun addWarehouse(@RequestBody warehouse: Warehouse): SaResult {
        return if (warehouseDao.insert(warehouse) > 0) SaResult.ok("增加仓库成功") else SaResult.error("增加仓库失败")
    }

    @GetMapping
    fun allWarehouse():SaResult{
        return SaResult.data(warehouseDao.selectAll())
    }

}