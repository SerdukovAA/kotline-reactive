package io.github.kotlin.backend.product.dao

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.util.*

@Table("products")
data class ProductSnapshot(@Id var id: UUID? = null, var name: String, var price: Int)
