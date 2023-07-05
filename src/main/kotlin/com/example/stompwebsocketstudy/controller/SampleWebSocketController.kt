package com.example.stompwebsocketstudy.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Controller
import java.time.OffsetDateTime

@Controller
class SampleWebSocketController {

//    @Autowired
//    private lateinit var messagingTemplate: SimpMessagingTemplate
//
//    @Scheduled(fixedDelay = 1000)
//    fun sendMessages() {
//        val message = Greeting("Hello, world! Now is : ${OffsetDateTime.now()}") // 전송할 메시지 생성
//        messagingTemplate.convertAndSend("/topic/greetings", message) // 해당 주제로 메시지 전송
//    }
}

class Greeting(content: String?) {
    var content: String? = content
        private set
}
