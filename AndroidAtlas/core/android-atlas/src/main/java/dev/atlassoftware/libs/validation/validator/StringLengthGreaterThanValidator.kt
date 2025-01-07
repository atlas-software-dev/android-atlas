package dev.atlassoftware.libs.validation.validator

class StringLengthGreaterThanValidator {
    operator fun invoke(value: String?, length: Int): Boolean {
        return !value.isNullOrBlank() && value.length > length
    }
}