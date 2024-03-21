package com.example.kuguanli

import org.mybatis.spring.annotation.MapperScan
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import

@SpringBootApplication
@MapperScan("com.example.kuguanli.dao")
@Import
class KuguanliApplication

fun main(args: Array<String>) {
    runApplication<KuguanliApplication>(*args)
}
