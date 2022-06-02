package com.podmev.shoppinglist.presentation

import androidx.lifecycle.ViewModel
import com.podmev.shoppinglist.data.ShopListRepositoryImpl
import com.podmev.shoppinglist.domain.AddShopItemUseCase
import com.podmev.shoppinglist.domain.EditShopItemUseCase
import com.podmev.shoppinglist.domain.GetShopItemUseCase
import com.podmev.shoppinglist.domain.ShopItem

class ShopItemViewModel:ViewModel() {
    private val repository = ShopListRepositoryImpl

    private val getShopItemUseCase = GetShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)
    private val addShopItemUseCase = AddShopItemUseCase(repository)

    fun getShopItem(shopItemId: Int): ShopItem{
        return getShopItemUseCase.getShopItem(shopItemId)
    }

    fun addShopItem(inputName: String?, inputCount: String?){
        val name = parseName(inputName)
        val count = parseCount(inputName)
        val fieldsValid = validateInput(name, count)
        if(fieldsValid) {
            val shopItem = ShopItem(name, count, true)
            addShopItemUseCase.addShopItem(shopItem)
        }
    }

    fun editShopItem(oldShopItem: ShopItem, inputName: String, inputCount: Int){
        val name = parseName(inputName)
        val count = parseCount(inputName)
        val fieldsValid = validateInput(name, count)
        if(fieldsValid) {
            val newShopItem = oldShopItem.copy(name = name, count = count)
            editShopItemUseCase.editShopItem(newShopItem)
        }
    }

    private fun parseName(inputName: String?): String{
        return inputName?.trim() ?: ""
    }

    private fun parseCount(inputCount: String?): Int{
        return inputCount?.trim()?.toIntOrNull() ?: 0
    }

    private fun validateInput(name: String, count: Int): Boolean{
        var result = true
        if(name.isBlank()){
            //TODO show error input name
            result = false
        }
        if(count <= 0 ){
            //TODO show error input count
            result = false
        }
        return result
    }
}