package com.example.mvc.model.http

import com.example.mvc.annotaion.StringFormatDataTime
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import java.lang.Exception
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.validation.constraints.*

//@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class UserRequest (

    @field:NotEmpty //왜 필드를 쓰는지 알아보자
    @field: Size(min=2, max=8)
    var name:String?=null,

    @field:PositiveOrZero //0 < 양수인지 확인
    var age:Int?=null,

    @field:Email //이메일 양식인지 확인
    var email:String?=null,

    @field:NotBlank //공백을 검증
    var address:String?=null,

    //@JsonProperty("phone_number")
    @field:Pattern(regexp="^\\d{3}-\\d{3,4}-\\d{4}\$") //정규식 검증
    var phoneNumber:String?=null,    //carmel case snake case -> phone_number

    @field:StringFormatDataTime("yyyy-MM-dd HH:mm:ss", message = "패턴이 올바르지 않습니다.")
    var createAT:String?=null //yyyy-MM-dd HH:mm:ss ex) 2020-10-02 13:01:24
)/*{
    @AssertTrue(message = "생성일자의 패턴은 yyyy-MM-dd HH:mm:ss 입니다.")
    private fun isValidCreatedAt():Boolean{//정상이면 true, 비정장이면 false
        return try{
            LocalDateTime.parse(this.createAT, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
            true
        }catch (e:Exception){
            false
        }
    }
}*/