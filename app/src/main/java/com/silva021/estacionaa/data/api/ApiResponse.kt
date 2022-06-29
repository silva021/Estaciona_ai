package com.silva021.estacionaa.data.api

import com.silva021.toolkit.model.ResultError

sealed class ApiResponse<T>(
    data: T? = null,
    exception: ResultError? = null,
) {
    data class Success<T>(val data: T) : ApiResponse<T>(data, null)
    data class Error<T>(val exception: ResultError) : ApiResponse<T>(null, exception)
}