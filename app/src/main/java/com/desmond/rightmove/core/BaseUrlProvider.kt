package com.desmond.rightmove.core

import com.desmond.rightmove.BuildConfig
import javax.inject.Inject

class BaseUrlProvider @Inject constructor() {

    val baseUrl: String
        get() = BuildConfig.BASE_URL
}