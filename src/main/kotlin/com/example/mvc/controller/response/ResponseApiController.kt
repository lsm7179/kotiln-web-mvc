package com.example.mvc.controller.response

import com.example.mvc.model.http.UserRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/response")
class ResponseApiController {
    //1.get 400 localhost:8080/api/response?age=10
    @GetMapping("")
    fun getMapping(@RequestParam(required = false) age:Int?): ResponseEntity<String> { //(required = false) : 필수값이 아니다. ? : 값이 없을 수도있다.
        //?를 엘비스연산자라고한다.
        //1. age==null -> 400 error
        return age?.let{  //age is not null
            if(it<20){
                //2.age < 20 -> 400 error
                return ResponseEntity.status(400).body("20살이상이어야 함")
            }
            ResponseEntity.ok("ok")
        }?:run{  //age is null
            return ResponseEntity.status(400).body("age값 내놔")
        }
    }
    //2.post 200
    @PostMapping("")
    fun postMapping(@RequestBody userRequest: UserRequest?): ResponseEntity<Any> {
        return ResponseEntity.status(200).body(userRequest)

    }
    //3.put 201
    @PutMapping("")
    fun pustMapping(@RequestBody userRequest: UserRequest?): ResponseEntity<UserRequest> {
        //1. 기존 데이터가 없어서 새로 생성했다.
        return ResponseEntity.status(HttpStatus.CREATED).body(userRequest)
    }
    //4.delete 500
    @DeleteMapping("/{id}")
    fun deleteMapping(@PathVariable id : Int): ResponseEntity<Any> {
        return ResponseEntity.status(500).body(null)
    }
}