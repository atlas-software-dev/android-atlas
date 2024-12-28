package com.atlas.atlas.validation.validator

class StringNotNullOrEmptyValidator
{
    operator fun invoke(value: String?) = !(value.isNullOrEmpty() || value.isBlank())
}