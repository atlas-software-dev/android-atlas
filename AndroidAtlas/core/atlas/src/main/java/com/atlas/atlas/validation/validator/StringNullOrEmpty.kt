package com.atlas.atlas.validation.validator

import com.atlas.atlas.validation.ValidationError
import com.atlas.atlas.validation.Validator
import java.lang.reflect.Field


class StringNullOrEmpty(override val field: Field): Validator<String> {
    override fun getDefaultErrorMessage()
    = "The field ${field.name} is empty, blank or null"
    override fun validate(value: String?, errorMessage: String?): ValidationError? {
        if (value.isNullOrEmpty() || value.isBlank()) {
            return error(if (errorMessage.isNullOrEmpty()) getDefaultErrorMessage() else errorMessage)
        }
        return null
    }
}