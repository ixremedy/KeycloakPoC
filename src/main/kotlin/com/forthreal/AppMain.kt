package com.forthreal

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
open class AppMain {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val application = SpringApplication(AppMain::class.java)
            val context = application.run( *args )
        }
    }
}