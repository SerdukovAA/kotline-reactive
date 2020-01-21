package io.github.kotlin.backend.product

import io.github.kotlin.backend.product.dto.AddProductDTO
import io.github.kotlin.backend.product.dto.ProductDTO
import io.github.kotlin.backend.product.services.ProductService
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/products")
class ProductController(private val productService: ProductService) {

    @GetMapping("/{id}")
    fun findById(@PathVariable id: UUID): ProductDTO {
        return productService.findById(id)
    }

    @PostMapping("/{id}/price/{price}")
    fun updatePrice(@PathVariable id: UUID, @PathVariable price: Int): ProductDTO {
        return productService.changePrice(id, price)
    }

    @PostMapping
    fun add(@RequestBody product: AddProductDTO): ProductDTO {
        return productService.add(product)
    }

    @GetMapping
    fun getAll(): List<ProductDTO> {
        return productService.getAll()
    }

    @PostMapping("ship/{id}/{items}")
    fun shipping(@PathVariable id: UUID, @PathVariable items: Int) {
         productService.shipping(id, items)
    }

    @PostMapping("sale/{id}/{items}")
    fun sale(@PathVariable id: UUID, @PathVariable items: Int) {
         productService.sale(id, items)
    }

}