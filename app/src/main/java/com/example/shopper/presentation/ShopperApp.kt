package com.example.shopper.presentation

import android.app.Application
import com.example.shopper.di.DaggerApplicationComponent

class ShopperApp : Application(){

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }

    override fun onCreate() {
        component.inject(this)
        super.onCreate()
    }

}