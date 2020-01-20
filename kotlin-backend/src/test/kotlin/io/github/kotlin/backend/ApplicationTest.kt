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

}