package com.caju.domain

import com.caju.utils.DateTimeUtils.buildDateLocal
import jakarta.enterprise.inject.Default
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(name = "transaction")
class Transaction {
    @Id
    val id: UUID = UUID.randomUUID()

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    lateinit var account: Account

    @Column(name = "total_amount")
    var totalAmount: Double = 0.0

    lateinit var mcc: String

    lateinit var merchant: String

    @Default
    @Column(name = "created_date")
    var createdDate: LocalDateTime = buildDateLocal()

    constructor()

    constructor(account: Account, totalAmount: Double, mcc: String, merchant: String) {
        this.account = account
        this.totalAmount = totalAmount
        this.mcc = mcc
        this.merchant = merchant
    }
}
