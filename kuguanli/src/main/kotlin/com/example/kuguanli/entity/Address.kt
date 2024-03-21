package com.example.kuguanli.entity

data class Address(
    var province: String,
    var city: String,
    var district: String,
    var detail: String
){
    constructor():this("","","","")
}
