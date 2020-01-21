package io.github.kotlin.backend.product.dao

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.util.*

@Table("sale")
data class Sale(@Id var id: UUID? = null, var productId: UUID, var items: Int)
