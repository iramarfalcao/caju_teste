package com.caju.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "account")
class Account {

    @Id
    @Column(name = "account_id")
    lateinit var accountId: String

    @Column(name = "food_balance")
    var foodBalance: Double = 0.0

    @Column(name = "meal_balance")
    var mealBalance: Double = 0.0

    @Column(name = "cash_balance")
    var cashBalance: Double = 0.0
}