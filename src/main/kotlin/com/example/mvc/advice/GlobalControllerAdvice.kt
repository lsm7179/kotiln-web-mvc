package com.example.mvc.advice

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.lang.RuntimeException

//@RestControllerAdvice(basePackageClasses = [ExceptionHandler::class])
class GlobalControllerAdvice { //restcontroller에 발생하는 에러를 잡는 클래스

    @ExceptionHandler(value=[RuntimeException::class])
    fun exception(e: RuntimeException):String{
        return "Server Error"
    }

    @ExceptionHandler(value=[IndexOutOfBoundsException::class])
    fun indexOutOfBoundsException(e:IndexOutOfBoundsException):ResponseEntity<String>{
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Index Error")
    }

}