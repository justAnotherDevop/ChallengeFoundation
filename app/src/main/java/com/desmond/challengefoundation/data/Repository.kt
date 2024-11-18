package com.desmond.challengefoundation.data

import javax.inject.Inject

class Repository @Inject constructor(
    private val service: ApiService,
) {
}