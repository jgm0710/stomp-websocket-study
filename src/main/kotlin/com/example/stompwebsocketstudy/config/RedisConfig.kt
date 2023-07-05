package com.example.stompwebsocketstudy.config

import com.example.stompwebsocketstudy.controller.Greeting
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.listener.ChannelTopic
import org.springframework.data.redis.listener.RedisMessageListenerContainer
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Service
import java.time.OffsetDateTime
import java.util.concurrent.atomic.AtomicInteger

@Configuration
class RedisConfig {

    @Autowired
    lateinit var receiver: Receiver

    @Bean
    fun lettuceConnectionFactory(): LettuceConnectionFactory {
        val lettuceConnectionFactory = LettuceConnectionFactory(RedisStandaloneConfiguration("localhost", 6407))
        lettuceConnectionFactory.setPassword("study")
        return lettuceConnectionFactory
    }

    @Bean
    fun redisTemplate(): RedisTemplate<String, Any> {
        val template = RedisTemplate<String, Any>()
        template.setConnectionFactory(lettuceConnectionFactory())
        return template
    }

    @Bean
    fun messageListenerAdapter(): MessageListenerAdapter {
        return MessageListenerAdapter(receiver, "receiveMessage")
    }

    @Bean
    fun container(): RedisMessageListenerContainer {
        val container = RedisMessageListenerContainer()
        container.setConnectionFactory(lettuceConnectionFactory())
        container.addMessageListener(messageListenerAdapter(), topic())
        return container
    }

    @Bean
    fun topic(): ChannelTopic {
        return ChannelTopic("messageChannel")
    }
}

@Service
class Receiver {

    @Autowired
    private lateinit var messagingTemplate: SimpMessagingTemplate

    private val counter = AtomicInteger()

    fun receiveMessage(message: String) {
        println("Received <$message>")
        counter.incrementAndGet()
        val _message =
            Greeting("RealMessage : $message, Hello, world! Now is : ${OffsetDateTime.now()}") // 전송할 메시지 생성
        messagingTemplate.convertAndSend("/topic/greetings", _message) // 해당 주제로 메시지 전송
    }

    fun getCount(): Int {
        return counter.get()
    }
}
