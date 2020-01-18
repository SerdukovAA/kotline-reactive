package io.github.kotlin.backend.product.services

import io.github.kotlin.backend.product.dao.ProductRepository
import io.github.kotlin.backend.product.dao.ProductSnapshot
import java.util.*

class Product(val repo: ProductRepository, snap: ProductSnapshot) {

    val id: UUID = checkNotNull(snap.id, {"Id should be set beforehand"})
    var name: String = snap.name
    var price: Int = snap.price

    fun save() {
        repo.save(getSpapshot())
    }

    private fun getSpapshot(): ProductSnapshot {
        return ProductSnapshot(this.id, this.name, this.price)
    }

}