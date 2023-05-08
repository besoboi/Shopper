package com.example.shopper.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.shopper.domain.ShopItem
import com.example.shopper.domain.ShopListRepository

class ShopListRepositoryImpl(
    application: Application
) : ShopListRepository {

    private val shopListDao = AppDatabase.getInstance(application).shopListDao()
    private val mapper = ShopListMapper()

    override suspend fun addShopItem(shopItem: ShopItem) {
        shopListDao.addShopItem(mapper.mapEntityToDbModel(shopItem))
    }

    override fun getShopList(): LiveData<List<ShopItem>> = MediatorLiveData<List<ShopItem>>()
        .apply {
            addSource(shopListDao.getShopList()) {
                value = mapper.mapListDbModelToListShopItem(it)
            }
        }

    override suspend fun getItemByID(shopItemId: Int): ShopItem {
        val dbModel = shopListDao.getShopItem(shopItemId)
        return mapper.mapDbModelToEntity(dbModel)
    }

    override suspend fun editShopItem(shopItem: ShopItem) {
        shopListDao.addShopItem(mapper.mapEntityToDbModel(shopItem))
    }

    override suspend fun deleteShopItem(shopItem: ShopItem) {
        shopListDao.deleteShopItem(shopItemId = shopItem.id)
    }

}