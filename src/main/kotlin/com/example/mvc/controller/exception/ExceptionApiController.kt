package com.example.mvc.controller.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.lang.RuntimeException

@RestController
@RequestMapping("/api/exception")
class ExceptionApiController {//예외가 발생했을때 처리
    @GetMapping("/hello")
    fun hello(){
        //if(true) throw RuntimeException("강제 에러 발생")
        val list= mutableListOf<String>()
        val temp = list[0]
    }

    @ExceptionHandler(value=[IndexOutOfBoundsException::class])
    fun indexOutOfBoundsException(e:IndexOutOfBoundsException): ResponseEntity<String> {
        //이렇게 하면 공통 안타고 여기를 태워서 간다.
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Index Error Inner Controller")
    }
}