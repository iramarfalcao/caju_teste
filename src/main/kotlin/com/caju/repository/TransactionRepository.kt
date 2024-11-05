package com.caju.repository

import com.caju.domain.Transaction
import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepository
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class TransactionRepository : PanacheRepository<Transaction>