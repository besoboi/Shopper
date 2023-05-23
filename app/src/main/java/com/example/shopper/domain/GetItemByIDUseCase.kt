package com.example.shopper.domain

import javax.inject.Inject

class GetItemByIDUseCase @Inject constructor(
    private val shopListRepository: ShopListRepository
    ) {
    suspend fun getItemByID(shopItemId: Int): ShopItem{
       return shopListRepository.getItemByID(shopItemId)
    }
}