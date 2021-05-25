package com.example.mvc.controller.page

import com.example.mvc.model.http.UserRequest
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller //Controller어노테이션이 붙으면 static 하위에 있는 html을 찾는다.
class PageController {
    @GetMapping("/main")
    fun main(): String {
        println("init main")
        return "main.html"
    }

    @ResponseBody //json 형태로 보낸다.
    @GetMapping("/test")
    fun response(): UserRequest {
        return UserRequest().apply {
            this.name="steve"
        }
    }
}