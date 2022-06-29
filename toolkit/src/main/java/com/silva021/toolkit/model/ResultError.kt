package com.silva021.toolkit.model

sealed class ResultError {
    class HttpError(val code: Int) : ResultError()
    class ExceptionError(val message: String) : ResultError()
}