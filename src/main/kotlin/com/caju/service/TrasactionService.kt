package com.caju.service

import com.caju.domain.Account
import com.caju.domain.Transaction
import com.caju.domain.enums.Category
import com.caju.repository.AccountRepository
import com.caju.repository.TransactionRepository
import com.caju.resource.request.TransactionRequest
import com.caju.resource.response.AccountResponse
import com.caju.resource.response.GenericResponse
import com.caju.resource.response.TransactionResponse
import jakarta.enterprise.context.ApplicationScoped
import jakarta.transaction.Transactional
import kotlin.String

@ApplicationScoped
class TransactionService(
    private val accountRepository: AccountRepository,
    private val transactionRepository: TransactionRepository
) {

    @Transactional
    fun authorizeTransaction(request: TransactionRequest): TransactionResponse {

        val account = accountRepository.findByAccountId(request.account) ?: return TransactionResponse("07")
        val category = mapMccToCategory(overrideMccByMerchant(request.merchant, request.mcc))
        val amount = request.totalAmount

        saveTransaction(request, account)

        return when {
            processTransaction(account, category, amount) -> TransactionResponse("00")
            category != "CASH" && processTransaction(account, "CASH", amount) -> TransactionResponse("00")
            else -> TransactionResponse("51")
        }
    }

    private fun processTransaction(account: Account, category: String, amount: Double): Boolean = when (category) {
        "FOOD" -> if (account.foodBalance >= amount) {
            account.foodBalance -= amount
            accountRepository.updateFoodBalance(account)
            true
        } else false

        "MEAL" -> if (account.mealBalance >= amount) {
            account.mealBalance -= amount
            accountRepository.updateMealBalance(account)
            true
        } else false

        "CASH" -> if (account.cashBalance >= amount) {
            account.cashBalance -= amount
            accountRepository.updateCashBalance(account)
            true
        } else false

        else -> false
    }

    fun mapMccToCategory(mcc: String): String = when (mcc) {
        "5411", "5412" -> Category.FOOD
        "5811", "5812" -> Category.MEAL
        else -> Category.CASH
    }.toString()

    fun overrideMccByMerchant(merchant: String, mcc: String): String = when {
        merchant.contains("UBER EATS") -> "5812"
        merchant.contains("UBER TRIP") -> "4121"
        merchant.contains("PAG*") -> "6012"
        merchant.contains("BILHETEUNICO") -> "4111"
        else -> mcc
    }

    fun saveTransaction(request: TransactionRequest, account: Account) = transactionRepository.persist(
        Transaction(
            account = account,
            totalAmount = request.totalAmount,
            mcc = request.mcc,
            merchant = request.merchant
        )
    )

    fun balance(accountId: String): Any {
        val account =
            accountRepository.findByAccountId(accountId) ?: return GenericResponse(message = "Conta não encontrada.")
        return AccountResponse(
            accountId = account.accountId,
            foodBalance = account.foodBalance,
            mealBalance = account.mealBalance,
            cashBalance = account.cashBalance
        )
    }
}