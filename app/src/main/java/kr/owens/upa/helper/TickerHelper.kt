package kr.owens.upa.helper

import kr.owens.upa.model.Ticker

object TickerHelper {
    private const val KOREA_WON = "KRW"

    fun filterFiat(tickers: List<Ticker>, fiat: String = KOREA_WON) =
        tickers.filter { it.market.startsWith(fiat) }

    fun sort(tickers: List<Ticker>) = tickers.sortedBy { it.koreanName }
}