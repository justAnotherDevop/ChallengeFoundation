package com.desmond.challengefoundation.data.di

import com.desmond.challengefoundation.core.BaseUrlProvider
import com.desmond.challengefoundation.data.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun provideOtherInterceptorOkHttpClient(
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(NETWORK_TIMEOUT_MINUTES, TimeUnit.MINUTES)
            .readTimeout(NETWORK_TIMEOUT_MINUTES, TimeUnit.MINUTES)
            .writeTimeout(NETWORK_TIMEOUT_MINUTES, TimeUnit.MINUTES)
            .build()
    }

    @Provides
    fun provideApiService(
        okHttpClient: OkHttpClient,
        baseUrlProvider: BaseUrlProvider,
    ) : ApiService {
        return Retrofit.Builder()
            .baseUrl(baseUrlProvider.baseUrl)
            .client(okHttpClient)
            .build()
            .create(ApiService::class.java)
    }
}

private const val NETWORK_TIMEOUT_MINUTES = 1L