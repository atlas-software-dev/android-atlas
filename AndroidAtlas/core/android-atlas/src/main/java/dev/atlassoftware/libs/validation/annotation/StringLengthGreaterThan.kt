package dev.atlassoftware.libs.validation.annotation

@Retention
@Target(AnnotationTarget.FIELD)
annotation class StringLengthGreaterThan(val length: Int = 0)