package com.atlas.atlas.validation.validator

class StringValidEmailValidator {
    operator fun invoke(email: String?): Boolean {
        return !email.isNullOrBlank() &&
                android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}