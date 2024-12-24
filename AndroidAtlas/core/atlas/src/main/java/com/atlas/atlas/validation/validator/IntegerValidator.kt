package com.atlas.atlas.validation.validator

sealed class IntegerValidator: Validator

class IntegerGreaterThanValidator(
    val value: Int = 0,
    override val fieldName: String = "Field",
    override val errorMessage: String = "The field $fieldName need to be greater than $value"
): IntegerValidator()

class IntegerGreaterThanOrEqualToValidator(
    val value: Int = 0,
    override val fieldName: String = "Field",
    override val errorMessage: String = "The field $fieldName need to be greater than or equal to $value"
): IntegerValidator()

class IntegerLessThanValidator(
    val value: Int = 0,
    override val fieldName: String = "Field",
    override val errorMessage: String = "The field $fieldName need to be less than $value"
): IntegerValidator()

class IntegerLessThanOrEqualToValidator(
    override val fieldName: String = "Field",
    override val errorMessage: String = "The field $fieldName is empty, blank or null",
    val value: Int = 0
)

