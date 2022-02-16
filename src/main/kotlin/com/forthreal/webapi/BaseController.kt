package com.forthreal.webapi

import com.alibaba.fastjson.JSONObject
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.util.MimeType

open abstract class BaseController {
    protected val STATUS_CODE = "STATUS"
    protected val SUCCESS_CODE = 0

    protected val RESPONSE_CONTENT = "response"

    fun prepareHttpHeaders(contentType: String) : HttpHeaders
    {
        val httpHeaders = HttpHeaders()
        httpHeaders.contentType = MediaType.asMediaType( MimeType.valueOf(contentType) )

        return httpHeaders
    }

    internal fun prepareHttpTextReply(jsonObject: JSONObject, httpHeaders: HttpHeaders) : ResponseEntity<String>
    {
        val httpStatus =
            jsonObject[STATUS_CODE]?.let {
                if( (it as Int) > 0 ) {
                    HttpStatus.BAD_REQUEST
                } else {
                    HttpStatus.OK
                }
            } ?: run {
                HttpStatus.ACCEPTED
            }

        return ResponseEntity.status( httpStatus ).headers(httpHeaders).body( jsonObject[RESPONSE_CONTENT] as String )
    }

}