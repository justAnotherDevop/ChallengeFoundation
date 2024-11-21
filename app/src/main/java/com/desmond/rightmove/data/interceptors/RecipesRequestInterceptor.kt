package com.desmond.rightmove.data.interceptors

import android.util.Log
import com.desmond.rightmove.core.RequestHelper
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class RecipesRequestInterceptor @Inject constructor(
    private val requestHelper: RequestHelper,
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val recipesRequest = chain.request()
            .newBuilder()
            .header(API_KEY_HEADER, requestHelper.apiKey)
            .build()

        Log.d(
            RecipesRequestInterceptor::class.java.simpleName,
            "Executing request with headers: ${recipesRequest.headers}"
        )

        return chain.proceed(recipesRequest)
    }

    companion object {
        private const val API_KEY_HEADER = "x-rapidapi-key"
    }
}