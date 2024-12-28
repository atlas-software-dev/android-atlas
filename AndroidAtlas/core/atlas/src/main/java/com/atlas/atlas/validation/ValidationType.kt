package com.atlas.atlas.validation

sealed class ValidationType(val field: String)

class StringNotNullOrEmptyType(field: String): ValidationType(field)
class StringLengthGreaterThanOrEqualToType(field: String, val length: Int): ValidationType(field)
class StringLengthGreaterThanType(field: String, val length: Int): ValidationType(field)
class StringLengthLessThanType(field: String, val length: Int): ValidationType(field)
class StringLengthLessThanOrEqualToType(field: String, val length: Int): ValidationType(field)
class StringValidEmailType(field: String): ValidationType(field)


