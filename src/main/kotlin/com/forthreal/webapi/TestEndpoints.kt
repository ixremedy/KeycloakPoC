package com.forthreal.webapi

import com.alibaba.fastjson.JSONObject
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest

@RestController
class TestEndpoints : BaseController() {

    @RequestMapping(value = ["/public/test-page"], method = [RequestMethod.GET])
    fun doPublicTest(request: HttpServletRequest) : ResponseEntity<String> {
        val jsonObject = JSONObject()
        jsonObject[STATUS_CODE] = SUCCESS_CODE
        jsonObject[RESPONSE_CONTENT] = "<h1>Welcome to the Public Page</h1>"

        return prepareHttpTextReply(jsonObject, prepareHttpHeaders("text/html"))
    }

    @RequestMapping(value = ["/private/welcome"], method = [RequestMethod.GET])
    fun doPrivateWelcome(request: HttpServletRequest) : ResponseEntity<String> {
        val jsonObject = JSONObject()
        jsonObject[STATUS_CODE] = SUCCESS_CODE
        jsonObject[RESPONSE_CONTENT] = "<h1>Welcome to the Inner Welcome Page, ${request.userPrincipal}</h1>"

        return prepareHttpTextReply(jsonObject, prepareHttpHeaders("text/html"))
    }

    @RequestMapping(value = ["/private/url1"], method = [RequestMethod.GET])
    fun doPrivateUrl1(request: HttpServletRequest) : ResponseEntity<String> {
        val jsonObject = JSONObject()
        jsonObject[STATUS_CODE] = SUCCESS_CODE
        jsonObject[RESPONSE_CONTENT] = "<h1>Welcome to the URL 1 Page, ${request.userPrincipal}</h1>"

        return prepareHttpTextReply(jsonObject, prepareHttpHeaders("text/html"))
    }

    @RequestMapping(value = ["/logout"], method = [RequestMethod.GET])
    fun doLogout(request: HttpServletRequest)  : ResponseEntity<String> {
        request.logout()

        val jsonObject = JSONObject()
        jsonObject[STATUS_CODE] = SUCCESS_CODE
        jsonObject[RESPONSE_CONTENT] = "<h1>You're logged out</h1>"

        return prepareHttpTextReply(jsonObject, prepareHttpHeaders("text/html"))
    }

}