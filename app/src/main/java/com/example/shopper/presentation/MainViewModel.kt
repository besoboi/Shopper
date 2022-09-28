package com.example.shopper.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shopper.data.ShopListRepositoryImpl
import com.example.shopper.domain.DeleteShopItemUseCase
import com.example.shopper.domain.EditShopItemUseCase
import com.example.shopper.domain.GetShopListUseCase
import com.example.shopper.domain.ShopItem

class MainViewModel : ViewModel() { //Если нужно обращение по context, то наследоваться от AndroidViewModel
    private val repository = ShopListRepositoryImpl //на данный момент реализация неверная, но
    //мы пока не выучили имплементацию зависимостей
    //в импорте есть зависимость от data слоя, что в корне не верно
    //в дальнейшем исправлю

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)

    val shopList = getShopListUseCase.getShopList()

    fun deleteShopItem(shopItem: ShopItem){
        deleteShopItemUseCase.deleteShopItem(shopItem)
    }

    fun changeEnableState(shopItem: ShopItem) {
        val newItem = shopItem.copy(enabled = !shopItem.enabled)
        editShopItemUseCase.editShopItem(newItem)
    }
}