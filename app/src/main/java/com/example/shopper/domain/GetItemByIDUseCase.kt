package com.example.shopper.domain

class GetItemByIDUseCase(private val shopListRepository: ShopListRepository) {
    fun getItemByID(shopItemId: Int): ShopItem{
       return shopListRepository.getItemByID(shopItemId)
    }
}