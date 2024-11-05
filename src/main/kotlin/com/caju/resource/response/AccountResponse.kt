package com.caju.resource.response

import kotlinx.serialization.Serializable

@Serializable
data class AccountResponse(
    var accountId: String,
    var foodBalance: Double,
    var mealBalance: Double,
    var cashBalance: Double
)