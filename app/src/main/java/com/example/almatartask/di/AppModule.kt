package com.example.almatartask.di

import android.content.Context
import androidx.room.Room
import com.example.almatartask.shopping.data.data_source.ShoppingDao
import com.example.almatartask.shopping.data.data_source.ShoppingDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
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


}