package com.atlas.atlas.validation

import kotlin.reflect.KClass

sealed class ValidationType(val field: String)

class NotNullOrEmptyType(field: String): ValidationType(field)
class StringLengthGreaterThanOrEqualToType(field: String, val length: Int): ValidationType(field)
class StringLengthGreaterThanType(field: String, val length: Int): ValidationType(field)
class StringLengthLessThanType(field: String, val length: Int): ValidationType(field)
class StringLengthLessThanOrEqualToType(field: String, val length: Int): ValidationType(field)
class StringValidEmailType(field: String): ValidationType(field)
class StringValidCPFType(field: String): ValidationType(field)
class LengthType(field: String, var min: Int, var max: Int): ValidationType(field)


