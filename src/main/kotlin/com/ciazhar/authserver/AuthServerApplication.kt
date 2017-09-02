package com.ciazhar.authserver

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
object AuthServerApplication {

    @JvmStatic
    fun main(args: Array<String>) {
        SpringApplication.run(AuthServerApplication::class.java, *args)
    }
}
