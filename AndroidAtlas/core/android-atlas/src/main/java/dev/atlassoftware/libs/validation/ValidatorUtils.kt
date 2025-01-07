package dev.atlassoftware.libs.validation
import dev.atlassoftware.libs.validation.validator.LengthValidator
import dev.atlassoftware.libs.validation.validator.StringLengthGreaterThanValidator
import dev.atlassoftware.libs.validation.validator.NotNullOrEmptyValidator
import dev.atlassoftware.libs.validation.validator.StringValidCPFValidator
import dev.atlassoftware.libs.validation.validator.StringValidEmailValidator

class ValidatorUtils {

    companion object {
        val notNullOrEmpty = NotNullOrEmptyValidator()
        val stringLengthGreaterThan = StringLengthGreaterThanValidator()
        val stringValidEmail = StringValidEmailValidator()
        val stringValidCPF = StringValidCPFValidator()
        val length = LengthValidator()
    }
}