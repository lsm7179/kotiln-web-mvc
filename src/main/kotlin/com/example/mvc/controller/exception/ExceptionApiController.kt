package com.example.mvc.controller.exception

import com.example.mvc.model.http.Error
import com.example.mvc.model.http.ErrorResponse
import com.example.mvc.model.http.UserRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.validation.FieldError
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.*
import java.lang.RuntimeException
import java.time.LocalDateTime
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.validation.ConstraintViolationException
import javax.validation.Valid
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@RestController
@RequestMapping("/api/exception")
@Validated
class ExceptionApiController {//예외가 발생했을때 처리
    @GetMapping("/hello")
    fun hello(){
        //if(true) throw RuntimeException("강제 에러 발생")
        val list= mutableListOf<String>()
        val temp = list[0]
    }

    @GetMapping("")
    fun get(
        @NotBlank
        @Size(min=2,max=6)
        @RequestParam name:String,

        @Min(10)
        @RequestParam age:Int
        ):String{
        println(name)
        println(age)
        return name+" "+age

    }

    @PostMapping("")
    fun post(@Valid @RequestBody userRequest: UserRequest): UserRequest {
        println(userRequest)
        return userRequest
    }

    @ExceptionHandler(value=[MethodArgumentNotValidException::class])
    fun methodArgumentNotValidException(e:MethodArgumentNotValidException,request: HttpServletRequest): ResponseEntity<ErrorResponse> {
        val errors = mutableListOf<Error>()
        e.bindingResult.allErrors.forEach { errorObejct ->
            val error=Error().apply {
                val field=errorObejct as FieldError
                this.field=field.field
                this.message=errorObejct.defaultMessage
                this.value=errorObejct.rejectedValue
            }
            errors.add(error)
        }

        //2. ErrorResponse
        val errorResponse=ErrorResponse().apply {
            this.resultCode="FAIL"
            this.httpStatus=HttpStatus.BAD_REQUEST.value().toString()
            this.httpMethod=request.method
            this.message="요청에 에러가 발청하였습니다."
            this.path=request.requestURI.toString()
            this.timestamp = LocalDateTime.now()
            this.errors=errors
        }
        //3. ResponseEntity
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse)
    }

    @ExceptionHandler(value=[ConstraintViolationException::class])
    fun constraintViolationException(e:ConstraintViolationException,request:HttpServletRequest):ResponseEntity<ErrorResponse>{
        //1. 에러 분석
        val errors= mutableListOf<Error>()
        e.constraintViolations.forEach {
            val error=Error().apply {
                this.field=it.propertyPath.last().name
                this.message=it.message
                this.value=it.invalidValue
            }
            errors.add(error)
        }

        //2. ErrorResponse
        val errorResponse=ErrorResponse().apply {
            this.resultCode="FAIL"
            this.httpStatus=HttpStatus.BAD_REQUEST.value().toString()
            this.httpMethod=request.method
            this.message="요청에 에러가 발청하였습니다."
            this.path=request.requestURI.toString()
            this.timestamp = LocalDateTime.now()
            this.errors=errors
        }
        //3. ResponseEntity
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse)

    }


    @ExceptionHandler(value=[IndexOutOfBoundsException::class])
    fun indexOutOfBoundsException(e:IndexOutOfBoundsException): ResponseEntity<String> {
        //이렇게 하면 공통 안타고 여기를 태워서 간다.
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Index Error Inner Controller")
    }
}