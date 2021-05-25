package com.example.mvc.validator

import com.example.mvc.annotaion.StringFormatDataTime
import java.lang.Exception
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class StringFormatDateTimeValidator:ConstraintValidator<StringFormatDataTime,String> {

    private var pattern : String ?=null
    override fun initialize(constraintAnnotation: StringFormatDataTime?) {
        this.pattern=constraintAnnotation?.pattern
        super.initialize(constraintAnnotation)
    }

    //정상이면 true, 비정상이면 false
    override fun isValid(value: String?, context: ConstraintValidatorContext?): Boolean {
        return try{
            LocalDateTime.parse(value, DateTimeFormatter.ofPattern(pattern))
            true
        }catch (e: Exception){
            false
        }
    }

}