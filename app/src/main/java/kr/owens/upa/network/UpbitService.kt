package kr.owens.upa.network

object UpbitService : BaseService() {
    private const val UPBIT_API_URL = "https://api.upbit.com/v1/"

    val client: UpbitApi = getClient(UPBIT_API_URL).create(UpbitApi::class.java)
}