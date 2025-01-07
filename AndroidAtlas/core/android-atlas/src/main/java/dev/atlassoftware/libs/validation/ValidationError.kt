package dev.atlassoftware.libs.validation

data class ValidationError (
    val type: ValidationType,
    val message: String?
)