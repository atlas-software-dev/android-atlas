package com.atlas.atlas.validation

class ValidationResult(
   private val errors: List<ValidationError> = listOf()
) {
   operator fun get(field: String) = errors.find { it.type.field == field }?.message
   fun getMessagesOrNull(field: String) = errors.filter { it.type.field == field }.map { it.message }
   fun getAllMessages() = errors.map { Pair(it.type.field, it.message) }
   fun isValid() = errors.isEmpty()
}