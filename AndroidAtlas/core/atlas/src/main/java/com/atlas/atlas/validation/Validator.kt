package com.atlas.atlas.validation
import java.lang.reflect.Field

interface Validator<T> {
    val field: Field
    fun getDefaultErrorMessage(): String
    fun validate(value: T?, errorMessage: String? = ""): ValidationError?
    fun error(message: String): ValidationError {
        return object : ValidationError {
            override val message = message
        }
    }
}
