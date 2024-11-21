package com.desmond.rightmove.core

import com.desmond.rightmove.BuildConfig
import javax.inject.Inject

class RequestHelper @Inject constructor() {

    val baseUrl: String
        get() = BuildConfig.BASE_URL

    val apiKey: String
        get() = BuildConfig.RAPID_API_KEY
}