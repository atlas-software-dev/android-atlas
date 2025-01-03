package com.atlas.atlas

import com.atlas.atlas.validation.Validator
import com.atlas.atlas.validation.annotation.Length
import com.atlas.atlas.validation.annotation.NotNullOrEmpty
import org.junit.Assert
import org.junit.Test

class LengthValidationTypeUnitTest {
    @Test
    fun sucessAnnotationTest() {
        val obj = object {
            @Length(6,8)
            val name: String = "12345678"
        }
        val result = Validator.validate(obj)
        Assert.assertEquals(true, result.isValid())
    }

    @Test
    fun successTest() {
        val obj = object {
            val name: String = "12345678"
        }
        val result = Validator.Builder()
                                .length(obj::name.name,6,8)
                                .build()
                                .validate(obj)
        Assert.assertEquals(true, result.isValid())
    }
    @Test
    fun errorUnderMinAnnotationTest() {
        val obj = object {
            @Length(6,8)
            val name: String = "123"
        }
        val result = Validator.validate(obj)
        Assert.assertEquals(false, result.isValid())
    }

    @Test
    fun errorUpperMaxTest() {
        val obj = object {
            val name: String = "123456789"
        }
        val result = Validator.Builder()
            .length(obj::name.name,6,8)
            .build()
            .validate(obj)
        Assert.assertEquals(false, result.isValid())
    }

    @Test
    fun errorNullTest() {
        val obj = object {
            @Length
            val name: String? = null
        }
        val result = Validator.validate(obj)
        Assert.assertEquals(true, result.isValid())
    }

    @Test
    fun errorInvalidType() {
        Assert.assertThrows(IllegalArgumentException::class.java) {
            val obj = object {
                @Length
                val name: Int = 0
            }
            val result = Validator.validate(obj)
        }
    }
}