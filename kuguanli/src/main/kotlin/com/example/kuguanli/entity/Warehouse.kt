package com.example.kuguanli.entity

data class Warehouse(
    var id: Int = 0,
    var name: String,
    var address: Address,
    var phoneNumber: String
){
    constructor() : this(0, "", Address(), "")
}