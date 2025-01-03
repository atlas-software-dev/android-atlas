package com.atlas.atlas.validation.annotation

@Retention
@Target(AnnotationTarget.FIELD)
annotation class Length(val min: Int = 0, val max: Int = Int.MAX_VALUE)