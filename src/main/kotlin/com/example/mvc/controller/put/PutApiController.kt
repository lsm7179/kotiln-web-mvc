package com.example.mvc.controller.put

import com.example.mvc.model.http.Result
import com.example.mvc.model.http.UserRequest
import com.example.mvc.model.http.UserResponse
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.validation.FieldError
import org.springframework.web.bind.annotation.*
import java.lang.reflect.Field
import javax.validation.Valid

@RestController
@RequestMapping("/api")
class PutApiController {

    @PutMapping("/put-mapping")
    fun putMapping():String{
        return "put-mapping"
    }

    @RequestMapping(method = [RequestMethod.PUT],path=["/request-mapping"])
    fun requestMapping():String{
        return "request-mapping-put method"
    }

    @PutMapping(path=["/put-mapping/object"])
    fun putMappingObject(@Valid @RequestBody userRequest: UserRequest, bindingResult:BindingResult) : ResponseEntity<String> {

        if(bindingResult.hasErrors()){
            //500 error 처리 하면 됨.
                var msg=StringBuilder()
            bindingResult.allErrors.forEach {
                val field=it as FieldError
                var message=it.defaultMessage
                msg.append(field.field+" : "+message+"\n")
            }
            return ResponseEntity.badRequest().body(msg.toString())
        }

        return ResponseEntity.ok("")
        //0. Response
        /*return UserResponse().apply {
            //1.result
            this.result = Result().apply {
                this.resultCode = "OK"
                this.resultMessage ="성공"
            }
        }.apply {
            //2.description
            this.description= "무야호~"
        }.apply {
            //3. user mutable list
            var userList = mutableListOf<UserRequest>()
            userList.add(userRequest)
            userList.add(UserRequest().apply {
                this.name="lsm"
                this.age =10
                this.email="a@email.com"
                this.address="lsm address"
                this.phoneNumber="111-2222-3333"
            })
            userList.add(UserRequest().apply {
                this.name="lsm2"
                this.age =20
                this.email="b@email.com"
                this.address="lsm3 address"
                this.phoneNumber="777-2222-3333"
            })
            this.user=userList
        }*/


    }

    //post와 put은 같은 바디를 사용할수 있다 그러나put은 없으면 등록 있으면 수정을 해준다.
}