package com.example.shopper.presentation

import androidx.lifecycle.ViewModel
import com.example.shopper.data.ShopListRepositoryImpl
import com.example.shopper.domain.AddShopItemUseCase
import com.example.shopper.domain.EditShopItemUseCase
import com.example.shopper.domain.GetItemByIDUseCase
import com.example.shopper.domain.ShopItem

class ShopItemViewModel : ViewModel() {

    private val repository = ShopListRepositoryImpl

    private val addShopItemUseCase = AddShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)
    private val getItemByIDUseCase = GetItemByIDUseCase(repository)

    fun getShopItem(shopItemId : Int){
        val item = getItemByIDUseCase.getItemByID(shopItemId)
    }

    fun addShopItem(inputName : String?, inputCount : String?){
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val fieldsValid = validateInput(name, count)
        if (fieldsValid) {
            val shopItem = ShopItem(name, count, true)
            addShopItemUseCase.addShopItem(shopItem)
        }
    }

    fun editShopItem(inputName : String?, inputCount : String?) {
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val fieldsValid = validateInput(name, count)
        if (fieldsValid) {
            val shopItem = ShopItem(name, count, true)
            editShopItemUseCase.editShopItem(shopItem)
        }
    }

    private fun parseName(inputName : String?) : String{
        return inputName?.trim() ?: ""
    }

    private fun parseCount(inputCount: String?) : Int{
        return try {
            inputCount?.trim()?.toInt() ?: 0
        } catch (e: Exception) {
            0
        }
    }

    private fun validateInput(name : String, count : Int) : Boolean {
        var result = true
        if (name.isBlank()) {
            // TODO : show err input name
            result = false
        }
        if (count <= 0) {
            // TODO : show err input count
            result = false
        }
        return result
    }
}
