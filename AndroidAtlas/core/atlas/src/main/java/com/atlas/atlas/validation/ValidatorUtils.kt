package com.atlas.atlas.validation
import com.atlas.atlas.validation.validator.StringLengthGreaterThanValidator
import com.atlas.atlas.validation.validator.StringNotNullOrEmptyValidator
import com.atlas.atlas.validation.validator.StringValidEmailValidator

class ValidatorUtils {

    companion object {
        val stringLengthGreaterThan = StringLengthGreaterThanValidator()
        val stringNotNullOrEmpty = StringNotNullOrEmptyValidator()
        val stringValidEmail = StringValidEmailValidator()
    }
}