package com.atlas.atlas

import com.atlas.atlas.validation.Validator
import com.atlas.atlas.validation.anotation.StringNotNullOrEmpty
import org.junit.Assert
import org.junit.Test

class IsNullOrEmptyValidationTypeUnitTest {
    @Test
    fun sucessAnnotationTest() {
        val obj = object {
            @StringNotNullOrEmpty
            val name: String = "Paulo"
        }
        val result = Validator.validate(obj)
        Assert.assertEquals(true, result.isValid())
    }

    @Test
    fun successTest() {
        val obj = object {
            val name: String = "Paulo"
        }
        val result = Validator.Builder()
                                .stringEmptyOrNull(obj::name.name)
                                .build()
                                .validate(obj)
        Assert.assertEquals(true, result.isValid())
    }
    @Test
    fun errorEmptyAnnotationTest() {
        val obj = object {
            @StringNotNullOrEmpty
            val name: String = ""
        }
        val result = Validator.validate(obj)
        Assert.assertEquals(false, result.isValid())
    }

    @Test
    fun errorEmptyTest() {
        val obj = object {
            val name: String = ""
        }
        val result = Validator.Builder()
            .stringEmptyOrNull(obj::name.name)
            .build()
            .validate(obj)
        Assert.assertEquals(false, result.isValid())
    }

    @Test
    fun errorBlankTest() {
        val obj = object {
            @StringNotNullOrEmpty
            val name: String = "        "
        }
        val result = Validator.validate(obj)
        Assert.assertEquals(false, result.isValid())
    }

    @Test
    fun errorInvalidType() {
        Assert.assertThrows(IllegalArgumentException::class.java) {
            val obj = object {
                @StringNotNullOrEmpty
                val name: Int = 0
            }
            val result = Validator.validate(obj)
        }
    }
}