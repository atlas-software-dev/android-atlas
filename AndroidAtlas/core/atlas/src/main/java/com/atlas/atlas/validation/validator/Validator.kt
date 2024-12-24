package com.atlas.atlas.validation.validator

import com.atlas.atlas.validation.ValidationResult

sealed interface Validator {
    val fieldName: String
    val errorMessage: String
}



