package com.developers.pk.models

data class CommonResponse<T>(
    val status: Boolean,
    val message: String,
    val data: T? = null
)
