package com.atlas.atlas.validation.validator

class LengthValidator
{
    operator fun invoke(value: Any?, min: Int, max: Int): Boolean {
            when (value) {
                is String? -> return value?.let { value.length in min..max } ?: true
                else -> throw IllegalArgumentException("type value not supported")
            }
    }
}