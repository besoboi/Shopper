package com.example.shopper.domain

import androidx.lifecycle.LiveData

interface ShopListRepository {
    suspend fun addShopItem(shopItem: ShopItem)
    fun getShopList(): LiveData<List<ShopItem>>
    suspend fun getItemByID(shopItemId: Int): ShopItem
    suspend fun editShopItem(shopItem: ShopItem)
    suspend fun deleteShopItem(shopItem: ShopItem)
}