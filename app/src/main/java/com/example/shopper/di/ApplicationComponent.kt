package com.example.shopper.di

import android.app.Application
import com.example.shopper.presentation.MainActivity
import com.example.shopper.presentation.ShopItemFragment
import com.example.shopper.presentation.ShopperApp
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [ViewModelModule::class, DataModule::class]
)
@ApplicationScope
interface ApplicationComponent {

    fun inject(application: ShopperApp)

    fun inject(activity: MainActivity)

    fun inject(fragment: ShopItemFragment)

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }
}