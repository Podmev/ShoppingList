package com.podmev.shoppinglist.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.podmev.shoppinglist.data.ShopListRepositoryImpl
import com.podmev.shoppinglist.domain.DeleteShopItemUseCase
import com.podmev.shoppinglist.domain.EditShopItemUseCase
import com.podmev.shoppinglist.domain.GetShopListUseCase
import com.podmev.shoppinglist.domain.ShopItem

//можно наследоваться от AndroidViewModel если нужен контект
class MainViewModel: ViewModel() {
    private val repository = ShopListRepositoryImpl

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)

    val shopList = MutableLiveData<List<ShopItem>>()

    fun getShopList(){
        val list = getShopListUseCase.getShopList()
        //.value можно использовать только в главном потоке, .postValue в любом
        shopList.value = list
    }

    fun changedEnabledShopItem(shopItem: ShopItem){
        val changedShopItem = shopItem.copy(enabled = !shopItem.enabled)
        editShopItemUseCase.editShopItem(changedShopItem)
        getShopList()
    }

    fun deleteShopItem(shopItem: ShopItem){
        deleteShopItemUseCase.deleteShopItem(shopItem)
        getShopList()
    }
}