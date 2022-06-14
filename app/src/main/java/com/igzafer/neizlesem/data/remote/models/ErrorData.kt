package com.igzafer.neizlesem.data.remote.models

import com.igzafer.neizlesem.domain.model.ErrorModel

data class ErrorData(
    val status_message: String,
    val status_code: Int
)

fun ErrorData.toErrorModel(): ErrorModel {
    return ErrorModel(
        statusMessage = status_message,
        statusCode = status_code
    )
}