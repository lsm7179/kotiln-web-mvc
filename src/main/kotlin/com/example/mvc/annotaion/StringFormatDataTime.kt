package com.example.mvc.annotaion

import com.example.mvc.validator.StringFormatDateTimeValidator
import javax.validation.Constraint
import javax.validation.Payload
import kotlin.reflect.KClass

@Constraint(validatedBy = [StringFormatDateTimeValidator::class])
@Target(
    AnnotationTarget.FIELD,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER,
)
@Retention(AnnotationRetention.RUNTIME) //runtime에만 사용
@MustBeDocumented
annotation class StringFormatDataTime(//커스텀 Annotation만들기
    val pattern: String = "yyyy-MM-dd HH:mm:ss",
    val message: String = "시간 형식이 유효하지 않습니다.",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)
