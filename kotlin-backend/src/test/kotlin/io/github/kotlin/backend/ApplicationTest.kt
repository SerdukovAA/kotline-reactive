package io.github.kotlin.backend

import io.github.kotlin.backend.product.dto.AddProductDTO
import io.github.kotlin.backend.product.dto.ProductDTO
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForObject
import org.springframework.boot.test.web.client.postForObject
import java.lang.Compiler.disable
import java.time.Duration
import java.util.*

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationTest {


    @Autowired
    lateinit var testRestTemplate: TestRestTemplate


    @Test
    fun test() {

        val result = testRestTemplate.getForObject<ProductDTO>("/products/2dc177c8-9ece-446a-bd7e-3600fa1b2c1b")
        print(result)


        val updatet = testRestTemplate.postForObject<ProductDTO>("/products/2dc177c8-9ece-446a-bd7e-3600fa1b2c1b/price/524")
        print(updatet)


        var result2 = testRestTemplate.getForObject<ProductDTO>("/products/2dc177c8-9ece-446a-bd7e-3600fa1b2c1b")
        print(result2)


        var resultlist = testRestTemplate.getForObject<List<ProductDTO>>("/products")
        print(resultlist)

    }


    @Test
    fun add_new_product() {

        val updatet = testRestTemplate.postForObject<ProductDTO>(
                "/products",
                AddProductDTO("Новый товар", 34)
        )
        print(updatet)

    }


    @Test
    fun shipping(){
         testRestTemplate.postForObject<Unit>(
                "/products/ship/42dc1e73-c64a-4c46-a430-34c0b79946d3/12",
                Unit
        )
    }
    @Test
    fun sale(){
         testRestTemplate.postForObject<Unit>(
                "/products/sale/42dc1e73-c64a-4c46-a430-34c0b79946d3/1",
                Unit
        )
    }

    val jsonMapper = ObjectMapper().apply {
        registerKotlinModule()
        disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
        setDateFormat(StdDateFormat())
    }


    @Test
    @RepeatedTest(10)
    fun textKafakProducer(){

        val fakePerson = Person(
                firstName = "Kohn",
                lastName = "Doow",
                birthDate = Date()
        )
        val fakePersonJson = jsonMapper.writeValueAsString(fakePerson)

        val producer = createProducer("kafka:9092")
        val futureResult = producer.send(ProducerRecord("ProductsSale", fakePersonJson))
        futureResult.get()
    }

    @Test
    fun textKafakConsume(){

        val consumer = createConsumer("kafka:9092")

        consumer.subscribe(listOf("ProductsSale"))

        while (true) {
            val records = consumer.poll(Duration.ofSeconds(1))
            print (records)
        }


    }
    private fun createProducer(brokers: String): Producer<String, String> {
        val props = Properties()
        props["bootstrap.servers"] = brokers
        props["key.serializer"] = StringSerializer::class.java.canonicalName
        props["value.serializer"] = StringSerializer::class.java.canonicalName
        return KafkaProducer<String, String>(props)
    }

    private fun createConsumer(brokers: String): Consumer<String, String> {
        val props = Properties()
        props["bootstrap.servers"] = brokers
        props["group.id"] = "person-processor"
        props["key.deserializer"] = StringDeserializer::class.java
        props["value.deserializer"] = StringDeserializer::class.java
        return KafkaConsumer<String, String>(props)
    }
}
data class Person(
        val firstName: String,
        val lastName: String,
        val birthDate: Date
)