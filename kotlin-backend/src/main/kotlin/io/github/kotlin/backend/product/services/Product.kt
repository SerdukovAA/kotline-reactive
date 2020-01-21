package io.github.kotlin.backend.product.services

import io.github.kotlin.backend.product.dao.*
import java.util.*

class Product(val repo: ProductRepository,
              val shipping: ShippingRepository,
              val sale: SaleRepository,
              snap: ProductSnapshot) {

    val id: UUID = checkNotNull(snap.id, {"Id should be set beforehand"})
    var name: String = snap.name
    var price: Int = snap.price

    fun save() {
        repo.save(getSpapshot())
    }

    private fun getSpapshot(): ProductSnapshot {
        return ProductSnapshot(this.id, this.name, this.price)
    }

    fun shipping(items: Int) {
        var ship = Shipping(productId = this.id, items = items)
        shipping.save(ship)
    }

    fun sale(items: Int) {
        var s = Sale(productId = this.id, items = items)
        sale.save(s)
    }

}