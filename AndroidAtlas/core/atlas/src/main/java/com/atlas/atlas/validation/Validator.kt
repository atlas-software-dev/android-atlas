package com.atlas.atlas.validation
import com.atlas.atlas.validation.anotation.StringNotNullOrEmpty
import com.atlas.atlas.validation.anotation.StringLengthGreaterThan
import com.atlas.atlas.validation.anotation.StringValidEmail
import org.apache.commons.lang3.reflect.FieldUtils
import java.lang.reflect.Field

class Validator(
    private val types: List<ValidationType> = listOf(),
    private val onGetMessageString: ((ValidationType) -> String)? = null,
)
{
    fun validate(value: Any): ValidationResult {
        val errors = mutableListOf<ValidationError>()
        getFields(value::class.java).forEach { field ->
            val annotationTypes = getTypesFromAnnotation(field)
            val fieldTypes = annotationTypes + types
                .filter { it.field.lowercase() ==  field.name.lowercase() }
            fieldTypes.forEach { type ->
                validateRule(type, field, value)?.let { error -> errors.add(error) }
            }
        }
        return ValidationResult(errors)
    }

    private fun getTypesFromAnnotation(field: Field): List<ValidationType> {
        val types = mutableListOf<ValidationType>()
        when {
            field.isAnnotationPresent(StringNotNullOrEmpty::class.java) -> {
                types.add(StringNotNullOrEmptyType(field.name))
            }
            field.isAnnotationPresent(StringValidEmail::class.java) -> {
                types.add(StringValidEmailType(field.name))
            }
            field.isAnnotationPresent(StringLengthGreaterThan::class.java) -> {
                val length = field.getAnnotation(StringLengthGreaterThan::class.java)?.length ?: 0
                types.add(StringLengthGreaterThanType(field.name, length))
            }
            else -> {}
        }
        return types
    }

    private fun getFields(classRef: Class<*>): List<Field> {
        return classRef.declaredFields.toList()
    }

    private fun validateRule(type: ValidationType, field: Field, value: Any): ValidationError? {
        return when (type) {
            is StringNotNullOrEmptyType -> {
                val target = FieldUtils.readField(field, value, true) as? String ?: run {
                    throw IllegalArgumentException()
                }
                if (!ValidatorUtils.stringNotNullOrEmpty(target)) {
                    ValidationError(type = type, message = onGetMessageString?.invoke(type))
                } else null
            }
            is StringLengthGreaterThanType -> {
                val target = FieldUtils.readField(field, value, true) as? String ?: run {
                    throw IllegalArgumentException()
                }
                if (!ValidatorUtils.stringLengthGreaterThan(target, type.length)) {
                    ValidationError(type = type, message = onGetMessageString?.invoke(type))
                } else null
            }
            is StringValidEmailType -> {
                val target = FieldUtils.readField(field, value, true) as? String ?: run {
                    throw IllegalArgumentException()
                }
                if (!ValidatorUtils.stringValidEmail(target)) {
                    ValidationError(type = type, message = onGetMessageString?.invoke(type))
                } else null
            }
            else -> null
        }
    }


    companion object {
        fun build() = Builder().build()
        fun validate(value: Any) = Builder().build().validate(value)
        fun addType(type: ValidationType) = Builder().addType(type)
        fun validate(value: Any, onGetMessageString: ((ValidationType) -> String)) =
            Builder().addOnGetMessageString(onGetMessageString).build().validate(value)
    }

    class Builder {
        private val _types: MutableList<ValidationType> = mutableListOf()
        private var onGetMessageString: ((ValidationType) -> String)? = null

        fun addOnGetMessageString(method: ((ValidationType) -> String)) = apply {
            onGetMessageString = method
        }
        fun addType(rule: ValidationType) = apply {
            _types.add(rule)
        }
        fun stringEmptyOrNull(field: String) = apply {
            _types.add(StringNotNullOrEmptyType(field))
        }
        fun validEmail(field: String) = apply {
            _types.add(StringValidEmailType(field))
        }
        fun stringGreaterThan(field: String, value: Int) = apply {
            _types.add(StringLengthGreaterThanType(field, value))
        }
        fun stringGreaterThanOrEqualTo(field: String, value: Int) = apply {
            _types.add(StringLengthGreaterThanOrEqualToType(field, value))
        }
        fun stringLengthLessThan(field: String, length: Int) = apply {
            _types.add(StringLengthLessThanType(field, length))
        }

        fun build() = Validator(
            types = _types.toList(),
            onGetMessageString = onGetMessageString)
    }
}
