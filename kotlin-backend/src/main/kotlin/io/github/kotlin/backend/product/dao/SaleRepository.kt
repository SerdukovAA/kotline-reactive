package io.github.kotlin.backend.product.dao

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface SaleRepository : CrudRepository<Sale, UUID>

