package com.alexanderpodkopaev.marvelcharacters.di

import com.alexanderpodkopaev.marvelcharacters.BuildConfig
import com.alexanderpodkopaev.marvelcharacters.data.MarvelApi
import com.alexanderpodkopaev.marvelcharacters.utils.md5
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class NetworkModule {

    private val BASE_URL = "http://gateway.marvel.com/v1/public/"

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient().newBuilder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
        .addInterceptor { chain ->
            val timestamp = System.currentTimeMillis().toString()
            val url = chain.request().url.newBuilder()
                .addQueryParameter("ts", timestamp)
                .addQueryParameter("apikey", BuildConfig.PUBLIC_KEY)
                .addQueryParameter("hash", (timestamp + BuildConfig.PRIVATE_KEY + BuildConfig.PUBLIC_KEY).md5())
                .build()
            val request = chain.request().newBuilder().url(url).build()
            chain.proceed(request)
        }
        .build()

    @Singleton
    @Provides
    fun provideJson() = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
    }

    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient, json: Json): Retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl(BASE_URL)
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build()

    @Singleton
    @Provides
    fun provideCharacterApi(retrofit: Retrofit): MarvelApi =
        retrofit.create(MarvelApi::class.java)
}