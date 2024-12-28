package com.atlas.atlas.validation.anotation

@Retention
@Target(AnnotationTarget.FIELD)
annotation class StringLengthGreaterThan(val length: Int = 0)