package com.atlas.atlas.validation
import com.atlas.atlas.validation.annotation.Length
import com.atlas.atlas.validation.annotation.NotNullOrEmpty
import com.atlas.atlas.validation.annotation.StringLengthGreaterThan
import com.atlas.atlas.validation.annotation.StringValidEmail
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
                validateRule(type, FieldUtils.readField(field, value, true))?.let { error -> errors.add(error) }
            }
        }
        return ValidationResult(errors)
    }

    private fun getTypesFromAnnotation(field: Field): List<ValidationType> {
        val types = mutableListOf<ValidationType>()
        when {
            field.isAnnotationPresent(NotNullOrEmpty::class.java) -> {
                types.add(NotNullOrEmptyType(field.name))
            }
            field.isAnnotationPresent(StringValidEmail::class.java) -> {
                types.add(StringValidEmailType(field.name))
            }
            field.isAnnotationPresent(StringLengthGreaterThan::class.java) -> {
                val length = field.getAnnotation(StringLengthGreaterThan::class.java)?.length ?: 0
                types.add(StringLengthGreaterThanType(field.name, length))
            }
            field.isAnnotationPresent(Length::class.java) -> {
                val annotation = field.getAnnotation(Length::class.java)
                types.add(LengthType(field.name, annotation?.min ?: 0,annotation?.max ?: Int.MAX_VALUE))
            }
            else -> {}
        }
        return types
    }

    private fun getFields(classRef: Class<*>): List<Field> {
        return classRef.declaredFields.toList()
    }

    private fun validateRule(type: ValidationType, value: Any?): ValidationError? {

        return when (type) {
            is NotNullOrEmptyType -> {
                if (!ValidatorUtils.notNullOrEmpty(value)) {
                    ValidationError(type = type, message = onGetMessageString?.invoke(type))
                } else null
            }
            is StringLengthGreaterThanType -> {
                val target = value?.let { value as? String ?: run { throw IllegalArgumentException() } }
                if (!ValidatorUtils.stringLengthGreaterThan(target, type.length)) {
                    ValidationError(type = type, message = onGetMessageString?.invoke(type))
                } else null
            }
            is StringValidEmailType -> {
                val target = value?.let { value as? String ?: run { throw IllegalArgumentException() } }
                if (!ValidatorUtils.stringValidEmail(target)) {
                    ValidationError(type = type, message = onGetMessageString?.invoke(type))
                } else null
            }
            is StringValidCPFType -> {
                val target = value?.let { value as? String ?: run { throw IllegalArgumentException() } }
                if (!ValidatorUtils.stringValidCPF(target)) {
                    ValidationError(type = type, message = onGetMessageString?.invoke(type))
                } else null
            }
            is LengthType -> {
                if (!ValidatorUtils.length(value,type.min,type.max)) {
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
        fun length(field: String, min: Int = 0, max: Int = Int.MAX_VALUE) = apply {
            _types.add(LengthType(field,min,max))
        }
        fun stringEmptyOrNull(field: String) = apply {
            _types.add(NotNullOrEmptyType(field))
        }
        fun validEmail(field: String) = apply {
            _types.add(StringValidEmailType(field))
        }
        fun validCPF(field: String) = apply {
            _types.add(StringValidCPFType(field))
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
