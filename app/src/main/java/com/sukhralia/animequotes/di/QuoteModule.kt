package com.sukhralia.animequotes.di

import android.app.Application
import androidx.room.Room
import com.sukhralia.animequotes.data.local.database.QuoteDatabase
import com.sukhralia.animequotes.data.remote.api.QuoteApi
import com.sukhralia.animequotes.data.repository.QuoteRepositoryImpl
import com.sukhralia.animequotes.domain.repository.QuoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object QuoteModule {

    @Provides
    @Singleton
    fun provideQuoteApi(): QuoteApi {
        return Retrofit.Builder().baseUrl(QuoteApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(QuoteApi::class.java)
    }

    @Provides
    @Singleton
    fun provideQuoteDatabase(app: Application): QuoteDatabase {
        return Room.databaseBuilder(
            app,
            QuoteDatabase::class.java,
            "quotes_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideQuoteRepository(db: QuoteDatabase, api: QuoteApi): QuoteRepository {
        return QuoteRepositoryImpl(api = api, db = db)
    }
}