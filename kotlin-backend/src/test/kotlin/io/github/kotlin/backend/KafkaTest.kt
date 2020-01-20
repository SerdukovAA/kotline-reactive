package io.github.kotlin.backend

import kotlinx.coroutines.delay
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.kafka.core.KafkaTemplate


@SpringBootTest
class KafkaTest {

    @Autowired
    lateinit var kafkaTemplate: KafkaTemplate<String, String>;

    fun sendMessage(msg: String) {
        kafkaTemplate.send("SimpleTopic23", msg)
    }

    @Test
    fun test() {
        sendMessage("My Super Message")
    }


}