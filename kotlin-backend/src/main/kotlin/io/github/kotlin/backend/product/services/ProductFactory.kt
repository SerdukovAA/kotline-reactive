package io.github.kotlin.backend.product.services

import io.github.kotlin.backend.product.dao.ProductRepository
import io.github.kotlin.backend.product.dao.ProductSnapshot
import org.springframework.stereotype.Service
import java.util.*

@Service
class ProductFactory (private val repo: ProductRepository) {

    fun findById(id: UUID): Product {
        val findById = repo.findById(id)
        return map(findById.orElseThrow { RuntimeException("Not found") })
    }

    private fun map(snap: ProductSnapshot): Product{
        var prod =  Product(this.repo, snap)
        return prod;
    }

    fun getAll(): List<Product> {
       return repo.findAll().asSequence().map { map (it) }.toList()
    }

    fun new(name: String, price: Int): Product {
        var prod =  ProductSnapshot( name = name, price = price)
        return map(this.repo.save(prod))
    }
}