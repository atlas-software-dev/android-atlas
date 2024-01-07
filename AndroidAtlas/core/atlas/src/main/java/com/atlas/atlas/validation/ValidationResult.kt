package com.atlas.atlas.validation

class ValidationResult(private val messages: Map<String, List<ValidationError>> = mapOf()) {
   operator fun get(id: String) = messages[id]?.firstOrNull()?.message
   fun getMessagesOrNull(id: String) = messages[id]?.map { e -> e.message }
   fun getAllMessages() = messages
   fun isValid() = messages.isEmpty()
}