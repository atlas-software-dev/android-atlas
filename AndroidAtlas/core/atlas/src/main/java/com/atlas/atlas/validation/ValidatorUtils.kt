package com.atlas.atlas.validation
import com.atlas.atlas.validation.validator.LengthValidator
import com.atlas.atlas.validation.validator.StringLengthGreaterThanValidator
import com.atlas.atlas.validation.validator.NotNullOrEmptyValidator
import com.atlas.atlas.validation.validator.StringValidCPFValidator
import com.atlas.atlas.validation.validator.StringValidEmailValidator

class ValidatorUtils {

    companion object {
        val notNullOrEmpty = NotNullOrEmptyValidator()
        val stringLengthGreaterThan = StringLengthGreaterThanValidator()
        val stringValidEmail = StringValidEmailValidator()
        val stringValidCPF = StringValidCPFValidator()
        val length = LengthValidator()
    }
}