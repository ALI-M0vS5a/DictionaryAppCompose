package com.example.dictionaryappcompose.di

import android.app.Application
import androidx.room.Room
import com.example.dictionaryappcompose.data.local.WordInfoDatabase
import com.example.dictionaryappcompose.data.local.converter.Converters
import com.example.dictionaryappcompose.data.remote.DictionaryApi
import com.example.dictionaryappcompose.data.repository.WordInfoRepositoryImpl
import com.example.dictionaryappcompose.data.util.JsonParserImpl
import com.example.dictionaryappcompose.domain.repository.WordInfoRepository
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDictionaryApi(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(DictionaryApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideWordInfoDatabase(app: Application): WordInfoDatabase {
        return Room.databaseBuilder(
            app,
            WordInfoDatabase::class.java,
            "word_db"
        ).addTypeConverter(Converters(JsonParserImpl(Gson())))
            .build()
    }

    @Provides
    @Singleton
    fun provideWordInfoRepository(api: DictionaryApi, db: WordInfoDatabase): WordInfoRepository {
        return WordInfoRepositoryImpl(api,db.dao)
    }
}