package io.github.kotlin.backend.product.services

import io.github.kotlin.backend.product.dao.ProductRepository
import io.github.kotlin.backend.product.dao.ProductSnapshot
import java.util.*

class Product(val id: UUID, var name: String, var price: Int) {

    lateinit var repo: ProductRepository;


    fun save() {
        repo.save(getSpapshot())
    }

    private fun getSpapshot(): ProductSnapshot {
        return ProductSnapshot(this.id, this.name, this.price)
    }

}