package com.example.shopper.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.shopper.domain.ShopItem
import com.example.shopper.domain.ShopListRepository
import kotlin.random.Random

object ShopListRepositoryImpl: ShopListRepository {

    private val shopListLiveData = MutableLiveData<List<ShopItem>>()
    private val shopList = sortedSetOf<ShopItem>({o1, o2 -> o1.id.compareTo(o2.id)}) //изменяемый список

    private var autoIncrementId = 0

    init {
        for (i in 0 until 1000) {
            val item = ShopItem("Name $i", i, Random.nextBoolean())
            addShopItem(item)
        }
    }
    override fun addShopItem(shopItem: ShopItem) {
        if (shopItem.id == ShopItem.UNDEFINED_ID) {
            shopItem.id = autoIncrementId++ }
        shopList.add(shopItem)
        updateList()
    }

    override fun getShopList(): LiveData<List<ShopItem>> {
        return shopListLiveData
    }

    override fun getItemByID(shopItemId: Int): ShopItem {
        return shopList.find{ //по сути работает как setrange + find
            it.id == shopItemId
        } ?: throw RuntimeException("Element with if $shopItemId not found")
        //^^ выкинет исключение в случае если find вернет null
    }

    override fun editShopItem(shopItem: ShopItem) {
        val oldElement = getItemByID(shopItem.id)
        shopList.remove(oldElement)
        addShopItem(shopItem)
    }

    override fun deleteShopItem(shopItem: ShopItem) {
        shopList.remove(shopItem)
        updateList()
    }

    private fun updateList() {
        shopListLiveData.value = shopList.toList()
        //toList нужен для создания копии списка,
        // для того, чтобы не было возможности изменять оригинальный List
        // Для того, чтоб
    }

}