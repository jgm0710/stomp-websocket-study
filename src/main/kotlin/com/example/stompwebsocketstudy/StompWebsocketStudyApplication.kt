package com.example.stompwebsocketstudy

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class StompWebsocketStudyApplication

fun main(args: Array<String>) {
    runApplication<StompWebsocketStudyApplication>(*args)
}
