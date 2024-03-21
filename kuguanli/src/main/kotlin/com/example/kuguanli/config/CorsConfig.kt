package com.example.kuguanli.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.servlet.config.annotation.CorsRegistry

import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


@Configuration
class CorsConfig : WebMvcConfigurer {
    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**") // 匹配的 URL
            .allowedOrigins("*") // 允许所有来源访问
            .allowedHeaders("*")
            .allowedMethods(CorsConfiguration.ALL)
            .maxAge(3600) // 预检请求的缓存时间，单位为秒
    }
}
