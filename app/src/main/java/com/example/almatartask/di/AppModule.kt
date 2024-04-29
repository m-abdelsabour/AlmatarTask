package com.example.almatartask.di

import android.content.Context
import androidx.room.Room
import com.example.almatartask.shopping.data.data_source.ShoppingDao
import com.example.almatartask.shopping.data.data_source.ShoppingDatabase
import com.example.almatartask.shopping.data.repository.ShoppingRepositoryImpl
import com.example.almatartask.shopping.domain.repository.ShoppingRepository
import com.example.almatartask.shopping.domain.usecase.AddShopping
import com.example.almatartask.shopping.domain.usecase.DeleteShopping
import com.example.almatartask.shopping.domain.usecase.GetShoppingItem
import com.example.almatartask.shopping.domain.usecase.GetShoppingList
import com.example.almatartask.shopping.domain.usecase.ShoppingUseCases
import com.example.almatartask.shopping.domain.usecase.UpdateShopping
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    /*  @Provides
      @Singleton
      fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
          level = HttpLoggingInterceptor.Level.BODY
      }

      @Provides
      @Singleton
      fun provideOkHttpClient(
          httpLoggingInterceptor: HttpLoggingInterceptor, apiKeyInterceptor: ApiKeyInterceptor
      ): OkHttpClient = OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor)
          .addInterceptor(apiKeyInterceptor).build()

      @Provides
      @Singleton
      fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
          Retrofit.Builder().baseUrl(BuildConfig.API_URL)
              .addConverterFactory(GsonConverterFactory.create())
              .addCallAdapterFactory(CoroutineCallAdapterFactory()).client(okHttpClient).build()

                @Provides
      @Singleton
      fun provideUserGson() = Gson()
  */

    @Singleton
    @Provides
    fun provideRoomDataBase(@ApplicationContext context: Context): ShoppingDatabase =
        Room.databaseBuilder(
            context,
            ShoppingDatabase::class.java,
            "shopping_database"
        )
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideRoomDao(db: ShoppingDatabase): ShoppingDao = db.dao

    @MainDispatcher
    @Provides
    fun provideMainDispatcher(): CoroutineDispatcher = Dispatchers.Main

    @IoDispatcher
    @Provides
    fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @Singleton
    fun provideShoppingRepository(dao: ShoppingDao,@IoDispatcher dispatcher: CoroutineDispatcher): ShoppingRepository {
        return ShoppingRepositoryImpl(dao,dispatcher)
    }
    @Provides
    @Singleton
    fun provideShoppingUseCases(repository: ShoppingRepository): ShoppingUseCases {
        return ShoppingUseCases(
            getShoppingItem = GetShoppingItem(repository),
            deleteShopping = DeleteShopping(repository),
            updateShopping = UpdateShopping(repository),
            addShopping = AddShopping(repository),
            getShoppingList = GetShoppingList(repository)
        )
    }

}