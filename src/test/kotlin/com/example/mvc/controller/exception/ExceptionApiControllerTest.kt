package com.example.mvc.controller.exception

import com.example.mvc.model.http.UserRequest
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.util.LinkedMultiValueMap

@WebMvcTest
@AutoConfigureMockMvc
class ExceptionApiControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    fun helloTest(){
        mockMvc.perform(
            MockMvcRequestBuilders.get("/api/exception/hello")
        ).andExpect(
            MockMvcResultMatchers.status().isOk
        ).andExpect (
            MockMvcResultMatchers.content().string("hello")
        ).andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun getTest(){
        val queryParams = LinkedMultiValueMap<String, String>()
        queryParams.add("name","steve")
        queryParams.add("age","20")

        mockMvc.perform(
            get("/api/exception").queryParams(queryParams)
        ).andExpect (
            status().isOk
        ).andExpect (
            content().string("steve 20")
        ).andDo(print())
    }
    @Test
    fun getFailTest(){
        val queryParams = LinkedMultiValueMap<String, String>()
        queryParams.add("name","steve")
        queryParams.add("age","9")

        mockMvc.perform(
            get("/api/exception").queryParams(queryParams)
        ).andExpect (
            status().isBadRequest
        ).andExpect (
            content().contentType("application/json")
        ).andExpect (
            jsonPath("\$.result_code").value("FAIL")
        ).andExpect (
            jsonPath("\$.errors[0].field").value("age")
        ).andExpect (
            jsonPath("\$.errors[0].value").value("9")
        ).andDo(print())
    }
    @Test
    fun postTest(){
        val userRequest=UserRequest().apply {
            this.name="lsm"
            this.age=10
            this.phoneNumber="010-1234-1234"
            this.address="경기도 성남시"
            this.email="lsm@gmail.com"
            this.createAT="2021-05-28 10:34:01"
        }
        val json= jacksonObjectMapper().writeValueAsString(userRequest)
        //println(json)
        mockMvc.perform(
            post("/api/exception")
                .content(json)
                .contentType("application/json")
                .accept("application/json")
        ).andExpect(
            status().isOk
        ).andExpect(
            jsonPath("\$.name").value("lsm")
        ).andExpect(
            jsonPath("\$.age").value("10")
        ).andExpect(
            jsonPath("\$.email").value("lsm@gmail.com")
        ).andDo(print())
    }

    @Test
    fun postFailTest(){
        val userRequest=UserRequest().apply {
            this.name="lsm"
            this.age=-1
            this.phoneNumber="010-1234-1234"
            this.address="경기도 성남시"
            this.email="lsm@gmail.com"
            this.createAT="2021-05-28 10:34:01"
        }
        val json= jacksonObjectMapper().writeValueAsString(userRequest)
        //println(json)
        mockMvc.perform(
            post("/api/exception")
                .content(json)
                .contentType("application/json")
                .accept("application/json")
        ).andExpect(
            status().isBadRequest
        ).andDo(print())
    }
}