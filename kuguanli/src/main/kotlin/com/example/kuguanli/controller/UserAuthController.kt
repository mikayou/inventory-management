package com.example.kuguanli.controller

import cn.dev33.satoken.annotation.SaCheckLogin
import cn.dev33.satoken.stp.StpUtil
import cn.dev33.satoken.util.SaResult
import com.example.kuguanli.dao.UserDao
import com.example.kuguanli.dto.UserLoginDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/user")

class UserAuthController(
    @Autowired val userDao: UserDao
) {
    @PostMapping("/login")
    fun login(@RequestBody loginDto: UserLoginDto): SaResult {
        var fetch= userDao.getUserByName(loginDto.name)
        if (fetch!=null){
            if (fetch.name == loginDto.name && fetch.password == loginDto.password) {
                StpUtil.login(fetch.uuid);
                return SaResult.data(StpUtil.getTokenInfo());
            }
        }
        return SaResult.error("登陆失败")

    }

    @RequestMapping("isLogin")
    fun isLogin(): SaResult? {
        return if (StpUtil.isLogin()) SaResult.ok() else SaResult.get(2001,"未登录",null)
    }

}