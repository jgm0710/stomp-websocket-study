package com.example.stompwebsocketstudy.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.data.redis.listener.ChannelTopic
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class MessageController @Autowired constructor(
    private val redisTemplate: StringRedisTemplate,
    private val topic: ChannelTopic,
) {

    @PostMapping("/publish")
    fun publishMessage(@RequestParam message: String) {
        redisTemplate.convertAndSend(topic.topic, message)
    }
}
