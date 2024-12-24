package com.atlas.atlas.validation.validator

sealed class StringValidator: Validator

class StringNullOrEmptyValidator(
    override val fieldName: String = "Field",
    override val errorMessage: String = "The field $fieldName is empty, blank or null"
): StringValidator()

class StringMaxLengthValidator(
    val maxLength: Int = 0,
    override val fieldName: String = "Field",
    override val errorMessage: String = "The field $fieldName need to have maximum length equal to $maxLength"
): StringValidator()