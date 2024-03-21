package com.example.kuguanli.entity

data class Product(
    var id: Int = 0,
    var name: String,
    var minInventory:Int,
    var maxInventory:Int,
    var catalogId: Int
)