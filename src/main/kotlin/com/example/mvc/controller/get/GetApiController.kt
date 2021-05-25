package com.example.mvc.controller.get

import com.example.mvc.model.http.UserRequest
import org.springframework.web.bind.annotation.*

@RestController //해당 어노테이션은 RestAPI라고 선언을 한것입니다.
@RequestMapping("/api") //http://localhost:8080/api
class GetApiController {
    @GetMapping(path= ["/hello","abcd"]) //GET http://localhost:8080/api/hello, GET http://localhost:8080/api/abcd
    fun hello():String{
        return "hello kotiln"
    }
    
    @RequestMapping(method =[RequestMethod.GET],path =  ["request-mapping"]) //restMethod를 여러개를 정의하여 동작이 된게 한다.
    fun requsetMapping(): String {
        return "request-mapping"
    }

    @GetMapping("get-mapping/path-variable/{name}/{age}") //GET http://localhost:8080/api/get-mapping/path-variable/{name}
    fun pathVariable(@PathVariable name:String,@PathVariable age:Int):String{
        println("${name},${age}")
        return name+" "+age
    }

    @GetMapping("get-mapping/path-variable2/{name}/{age}") //GET http://localhost:8080/api/get-mapping/path-variable2/{name}
    fun pathVariable2(@PathVariable(value = "name") _name:String,@PathVariable(name="age")age:Int):String{
        var name = "kotlin"
        println("${_name},${age}")
        return _name+" "+age
    }

    //쿼리파라미터사용 https://localhost:8080/api/page?key=valus&key=value
    @GetMapping("/get-mapping/guery-param") // ? name=steve&age=20
    fun queryParam(
        @RequestParam name:String,
        @RequestParam age:Int
    ):String{
        println("${name}, ${age}")
        return name+" "+age
    }

    // name , age, address , email
    // -
    // phoneNumber -> phone-number 그런데 코틀린에서는 변수명에 하이픈을 사용할수없다 그래서 객체로는 못받고 @RequestParam에 name또는 value 속성을 이용하여 받는다.
    @GetMapping("/get-mapping/query-param/object") //RestController 를 선언하면 리턴타입이 오브젝트일때 json형태로 보내준다.
    fun queryParamObject(userRequest: UserRequest):UserRequest{
        println(userRequest)
        return userRequest
    }

    @GetMapping("/get-mapping/query-param/map")
    fun queryParamMap(@RequestParam map:Map<String,Any>):Map<String,Any>{
        println(map)
        var phoneNumber=map.get("phone-number")
        return map
    }
}