package com.podmev.shoppinglist.domain

class EditShopItemUseClass(private val shopListRepository: ShopListRepository) {
    fun editShopItem(shopItem: ShopItem){
        shopListRepository.editShopItem(shopItem)
    }
}