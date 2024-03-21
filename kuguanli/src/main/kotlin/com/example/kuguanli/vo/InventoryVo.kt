package com.example.kuguanli.vo

data class InventoryVo(
    var productId:Int,
    var productName:String,
    var inventory:Int,
){
    constructor():this(0,"",0)
}
