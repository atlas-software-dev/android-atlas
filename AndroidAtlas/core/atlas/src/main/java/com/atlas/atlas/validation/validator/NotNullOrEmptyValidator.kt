package com.atlas.atlas.validation.validator

class NotNullOrEmptyValidator
{
    operator fun invoke(value: Any?): Boolean {
        value?.let {
            when (it) {
                is String -> return !(it.isEmpty() || it.isBlank())
                else -> throw IllegalArgumentException("type value not supported")
            }
        } ?: return false
    }
}