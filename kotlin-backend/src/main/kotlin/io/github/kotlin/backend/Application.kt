package io.github.kotlin.backend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories
import org.springframework.kafka.annotation.EnableKafka

@SpringBootApplication
@EnableJdbcRepositories
@EnableKafka
class Application

fun main(args: Array<String>) {
	runApplication<Application>(*args)

}
