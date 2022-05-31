package com.podmev.shoppinglist.data

import com.podmev.shoppinglist.domain.ShopItem
import com.podmev.shoppinglist.domain.ShopListRepository
import java.lang.RuntimeException

object ShopListRepositoryImpl: ShopListRepository {
    private val shopList = mutableListOf<ShopItem>()

    private var autoIncrementId = 0

    init {
        for (i in 1..10){
            addShopItem(ShopItem("ShopItem$i", 1, true))
        }
    }
    override fun addShopItem(shopItem: ShopItem) {
        if(shopItem.id==ShopItem.UNDEFINED_ID){
            shopItem.id = autoIncrementId++
        }
        shopList.add(shopItem)
    }

    override fun deleteShopItem(shopItem: ShopItem) {
        shopList.remove(shopItem)
    }

    override fun editShopItem(shopItem: ShopItem) {
        val oldElement = getShopItem(shopItem.id)
        shopList.remove(oldElement)
        addShopItem(shopItem)
    }

    override fun getShopItem(shopItemId: Int): ShopItem {
        return shopList.find{
            it.id == shopItemId
        } ?: throw RuntimeException("Couldn't find element by id $shopItemId")
    }

    override fun getShopList(): List<ShopItem> {
        return shopList.toList()
    }
}