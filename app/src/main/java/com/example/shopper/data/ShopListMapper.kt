package com.example.shopper.data

import com.example.shopper.domain.ShopItem

class ShopListMapper {
    fun mapEntityToDbModel(shopItem: ShopItem): ShopItemDbModel = ShopItemDbModel(
        id = shopItem.id,
        name = shopItem.name,
        count = shopItem.count,
        enabled = shopItem.enabled
    )

    fun mapDbModelToEntity(shopItemDbModel: ShopItemDbModel): ShopItem = ShopItem(
        id = shopItemDbModel.id,
        name = shopItemDbModel.name,
        count = shopItemDbModel.count,
        enabled = shopItemDbModel.enabled
    )

    fun mapListDbModelToListShopItem(list: List<ShopItemDbModel>) = list.map{
        mapDbModelToEntity(it)
    }
}