package com.example.shopper.domain

class GetItemByIDUseCase(private val shopListRepository: ShopListRepository) {
    suspend fun getItemByID(shopItemId: Int): ShopItem{
       return shopListRepository.getItemByID(shopItemId)
    }
}