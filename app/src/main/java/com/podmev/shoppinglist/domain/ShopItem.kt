package com.podmev.shoppinglist.domain

data class ShopItem(
    var id: Int,
    var name: String,
    var count: Int,
    var visible: Boolean
)
