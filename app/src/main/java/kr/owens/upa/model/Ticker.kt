package kr.owens.upa.model

import com.google.gson.annotations.SerializedName

data class Ticker(
    @SerializedName("market")
    val market: String,
    @SerializedName("korean_name")
    val koreanName: String,
    @SerializedName("english_name")
    val englishName: String
)
