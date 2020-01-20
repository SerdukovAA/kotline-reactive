package io.github.kotlin.backend.kafka

import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class KotlinConsumer {
    private val logger = LoggerFactory.getLogger(javaClass)
    @KafkaListener(topics = ["SimpleTopic"], groupId = "simple-consumer")
    fun processMessage(message: String) {
        logger.info("got message: {}", message)
    }
}
