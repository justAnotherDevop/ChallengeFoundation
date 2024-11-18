package com.desmond.challengefoundation.core

import android.os.Build
import com.desmond.challengefoundation.BuildConfig
import javax.inject.Inject

class BaseUrlProvider @Inject constructor() {

    val baseUrl: String
        get() = BuildConfig.BASE_URL
}