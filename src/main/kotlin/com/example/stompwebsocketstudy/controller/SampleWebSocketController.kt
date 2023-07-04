package com.example.stompwebsocketstudy.controller

import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.stereotype.Controller
import org.springframework.web.util.HtmlUtils

@Controller
class SampleWebSocketController {

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    @Throws(Exception::class)
    fun greeting(message: HelloMessage): Greeting? {
        Thread.sleep(1000) // simulated delay
        return Greeting("Hello, " + message.name?.let { HtmlUtils.htmlEscape(it) } + "!")
    }
}

class HelloMessage {
    var name: String? = null

    constructor()
    constructor(name: String?) {
        this.name = name
    }
}

class Greeting {
    var content: String? = null
        private set

    constructor()
    constructor(content: String?) {
        this.content = content
    }
}
