package com.desmond.rightmove.data.di

import com.desmond.rightmove.core.RequestHelper
import com.desmond.rightmove.data.RecipesService
import com.desmond.rightmove.data.interceptors.RecipesRequestInterceptor
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    fun provideOtherInterceptorOkHttpClient(
        recipesRequestInterceptor: RecipesRequestInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(recipesRequestInterceptor)
            .connectTimeout(NETWORK_TIMEOUT_MINUTES, TimeUnit.MINUTES)
            .readTimeout(NETWORK_TIMEOUT_MINUTES, TimeUnit.MINUTES)
            .writeTimeout(NETWORK_TIMEOUT_MINUTES, TimeUnit.MINUTES)
            .build()
    }

    @Provides
    fun provideApiService(
        okHttpClient: OkHttpClient,
        requestHelper: RequestHelper,
        moshi: Moshi,
    ) : RecipesService {
        return Retrofit.Builder()
            .baseUrl(requestHelper.baseUrl)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(RecipesService::class.java)
    }
}

private const val NETWORK_TIMEOUT_MINUTES = 1L