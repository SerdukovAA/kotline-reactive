package io.github.kotlin.backend.product.services

import io.github.kotlin.backend.product.dto.AddProductDTO
import io.github.kotlin.backend.product.dto.ProductDTO
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class ProductService(private var productFactory: ProductFactory) {

    fun findById(id: UUID) =
            map(productFactory.findById(id))

    private fun map(snap: Product) = ProductDTO(snap.id, snap.name, snap.price)

    @Transactional
    fun changePrice(id: UUID, price: Int): ProductDTO {
        var product = productFactory.findById(id)
        product.price = price
        product.save()
        return map(product)
    }

    fun getAll(): List<ProductDTO> {
        return productFactory.getAll().asSequence().map { map(it) }.toList()
    }

    fun add(product: AddProductDTO): ProductDTO {
        var prod: Product = productFactory.new(product.name, product.price)
        return map(prod)
    }

    @Transactional
    fun shipping(id: UUID, items: Int) {
        var product = productFactory.findById(id)
        product.shipping(items)
    }

    fun sale(id: UUID, items: Int) {
        var product = productFactory.findById(id)
        product.sale(items)
    }

}