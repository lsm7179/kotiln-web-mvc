package com.example.mvc.controller.post

import com.example.mvc.model.http.UserRequest
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class PostApiController {

    @PostMapping("/post-mapping")
    fun postMapping():String{
        return "post-mapping"
    }

    @RequestMapping(method = [RequestMethod.POST],path = ["/request-mapping"]) //예전에 사용하던 방식
    fun requestMapping():String{
        return "request-mapping"
    }

    //object  mapper
    //json -> object
    //object -> json
    @PostMapping("/post-mapping/object")
    fun postMappingObject(@RequestBody userRequest: UserRequest):UserRequest{
        //json -> object
        println(userRequest)
        return userRequest  //object -> json
    }
}