package com.example.shopper.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopper.data.ShopListRepositoryImpl
import com.example.shopper.domain.DeleteShopItemUseCase
import com.example.shopper.domain.EditShopItemUseCase
import com.example.shopper.domain.GetShopListUseCase
import com.example.shopper.domain.ShopItem
import kotlinx.coroutines.launch

class MainViewModel(application: Application) :
    AndroidViewModel(application) { //Если нужно обращение по context, то наследоваться от AndroidViewModel
    private val repository =
        ShopListRepositoryImpl(application) //на данный момент реализация неверная, но
    //мы пока не выучили имплементацию зависимостей
    //в импорте есть зависимость от data слоя, что в корне не верно
    //в дальнейшем исправлю

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)

    val shopList = getShopListUseCase.getShopList()

    fun deleteShopItem(shopItem: ShopItem) {
        viewModelScope.launch {
            deleteShopItemUseCase.deleteShopItem(shopItem)
        }

    }

    fun changeEnableState(shopItem: ShopItem) {
        viewModelScope.launch {
            val newItem = shopItem.copy(enabled = !shopItem.enabled)
            editShopItemUseCase.editShopItem(newItem)
        }
    }

}