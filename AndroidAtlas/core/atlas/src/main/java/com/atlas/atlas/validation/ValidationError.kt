package com.atlas.atlas.validation

data class ValidationError (
    val type: ValidationType,
    val message: String?
)