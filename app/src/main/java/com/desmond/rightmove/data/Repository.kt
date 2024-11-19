package com.desmond.rightmove.data

import javax.inject.Inject

class Repository @Inject constructor(
    private val service: ApiService,
) {
}