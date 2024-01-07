package com.atlas.atlas.validation

import com.atlas.atlas.validation.anotation.IsNullOrEmpty
import com.atlas.atlas.validation.validator.StringNullOrEmpty
import org.apache.commons.lang3.reflect.FieldUtils
import java.lang.reflect.Field

object ValidatorUtils {

    fun validate(value: Any?): ValidationResult {
        if (value == null) throw IllegalArgumentException()
        val errorMap = mutableMapOf<String, List<ValidationError>>()
        getFields(value.javaClass).forEach { field: Field ->
            val errors = mutableListOf<ValidationError>()
            when {
                field.isAnnotationPresent(IsNullOrEmpty::class.java) -> {
                val annotation = field.getAnnotation(IsNullOrEmpty::class.java)
                if (!field.type.isAssignableFrom(String::class.java))
                    throw IllegalArgumentException("wrong type for annotation")
                val result = StringNullOrEmpty(field)
                    .validate(
                        FieldUtils.readField(field, value, true) as String,
                        annotation?.errorMsg
                    )
                if (result != null)
                    errors += result
                }
                else -> {}
            }
            if (errors.isNotEmpty()) {
                errorMap[field.name] = errors
            }
        }
        return ValidationResult(errorMap)
    }

    // Returns a list with every field inside a class
    private fun getFields(classRef: Class<*>): List<Field> {
        return classRef.declaredFields.toList()
    }
}