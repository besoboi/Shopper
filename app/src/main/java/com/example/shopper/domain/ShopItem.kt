package com.example.shopper.domain

data class ShopItem(
    val name: String,
    val count: Int,
    val enabled: Boolean, //val это типа константы которую можно задать единожды в рантайме
    var id: Int = UNDEFINED_ID //var можно менять
) {
    companion object {
        const val UNDEFINED_ID = -1 //const можно задать только в compiletime
    }
}
