package com.podmev.shoppinglist.presentation

import androidx.lifecycle.ViewModel
import com.podmev.shoppinglist.data.ShopListRepositoryImpl
import com.podmev.shoppinglist.domain.DeleteShopItemUseCase
import com.podmev.shoppinglist.domain.EditShopItemUseCase
import com.podmev.shoppinglist.domain.GetShopListUseCase
import com.podmev.shoppinglist.domain.ShopItem

//можно наследоваться от AndroidViewModel если нужен контект
class MainViewModel : ViewModel() {
    private val repository = ShopListRepositoryImpl

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)

    val shopList = getShopListUseCase.getShopList()

    fun changedEnabledShopItem(shopItem: ShopItem) {
        val changedShopItem = shopItem.copy(enabled = !shopItem.enabled)
        editShopItemUseCase.editShopItem(changedShopItem)
    }

    fun deleteShopItem(shopItem: ShopItem) {
        deleteShopItemUseCase.deleteShopItem(shopItem)
    }
}