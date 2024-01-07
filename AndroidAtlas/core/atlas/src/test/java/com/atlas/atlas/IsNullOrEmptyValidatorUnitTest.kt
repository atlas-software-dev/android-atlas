package com.atlas.atlas

import com.atlas.atlas.validation.ValidatorUtils
import com.atlas.atlas.validation.anotation.IsNullOrEmpty
import org.junit.Assert
import org.junit.Test

class IsNullOrEmptyValidatorUnitTest {
    @Test
    fun sucessTest() {
        val obj = object {
            @IsNullOrEmpty("O campo Nome é obrigatório")
            val name: String = "Paulo"
        }
        val result = ValidatorUtils.validate(obj)
        Assert.assertEquals(true, result.isValid())
    }
    @Test
    fun errorEmptyTest() {
        val obj = object {
            @IsNullOrEmpty("O campo Nome é obrigatório")
            val name: String = ""
        }
        val result = ValidatorUtils.validate(obj)
        Assert.assertEquals(false, result.isValid())
    }

    @Test
    fun errorBlankTest() {
        val obj = object {
            @IsNullOrEmpty("O campo Nome é obrigatório")
            val name: String = "        "
        }
        val result = ValidatorUtils.validate(obj)
        Assert.assertEquals(false, result.isValid())
    }

    @Test
    fun errorInvalidType() {
        Assert.assertThrows(IllegalArgumentException::class.java) {
            val obj = object {
                @IsNullOrEmpty("O campo Nome é obrigatório")
                val name: Int = 0
            }
            val result = ValidatorUtils.validate(obj)
        }
    }

    @Test
    fun errorNull() {
        Assert.assertThrows(IllegalArgumentException::class.java) {
            ValidatorUtils.validate(null)
        }
    }
}