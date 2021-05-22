package kr.owens.upa.model

import com.google.gson.annotations.SerializedName

data class Price(
    @SerializedName("trade_price")
    val tradePrice: String,
)
