package kr.owens.upa.network

import kr.owens.upa.model.Price
import kr.owens.upa.model.Ticker
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface UpbitApi {
    @GET("market/all")
    @Headers("Accept: application/json")
    suspend fun getTickers(@Query("isDetails") isDetails: Boolean): Response<List<Ticker>>

    @GET("ticker")
    @Headers("Accept: application/json")
    suspend fun getCurrentPrice(@Query("markets") markets: String): Response<List<Price>>
}