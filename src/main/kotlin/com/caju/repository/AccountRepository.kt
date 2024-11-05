package com.caju.repository

import com.caju.domain.Account
import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepository
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class AccountRepository : PanacheRepository<Account> {

    fun findByAccountId(accountId: String) = find("id", accountId).firstResult()

    fun updateFoodBalance(account: Account) =
        update("foodBalance = ?1 where id = ?2", account.foodBalance, account.accountId)

    fun updateMealBalance(account: Account) =
        update("mealBalance = ?1 where id = ?2", account.mealBalance, account.accountId)

    fun updateCashBalance(account: Account) =
        update("cashBalance = ?1 where id = ?2", account.cashBalance, account.accountId)
}