package com.caju.resource.request

import kotlinx.serialization.Serializable

@Serializable
data class TransactionRequest(
    val account: String,
    val totalAmount: Double,
    val mcc: String,
    val merchant: String
)