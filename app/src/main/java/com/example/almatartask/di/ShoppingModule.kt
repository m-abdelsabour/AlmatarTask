package com.example.almatartask.di

import com.example.almatartask.shopping.data.repository.ShoppingRepositoryImpl
import com.example.almatartask.shopping.domain.repository.ShoppingRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class ShoppingModule {
    @Binds
    abstract fun bindShoppingRepository(imp: ShoppingRepositoryImpl): ShoppingRepository
}