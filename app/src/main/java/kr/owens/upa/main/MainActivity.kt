package kr.owens.upa.main

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kr.owens.upa.R
import kr.owens.upa.databinding.ActivityMainBinding
import kr.owens.upa.helper.AlarmHelper
import kr.owens.upa.helper.TickerHelper
import kr.owens.upa.model.Ticker
import kr.owens.upa.network.UpbitService
import kr.owens.upa.service.Actions
import kr.owens.upa.service.UpbitPriceAlarmService

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private lateinit var tickers: List<Ticker>

    private lateinit var alarmHelper: AlarmHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        alarmHelper = AlarmHelper.getInstance(null, null)

        val items = mutableListOf<String>()

        getTickersFromUpbit(items)

        setTheme(R.style.Theme_Upbit_price_alarm)
        setContentView(binding.root)

        val adapter = ArrayAdapter(applicationContext, R.layout.list_item, items)
        binding.coinSpinner.setAdapter(adapter)

        initializeButton()
    }

    private fun getTickersFromUpbit(items: MutableList<String>) {
        CoroutineScope(Dispatchers.IO).launch {
            UpbitService.client.getTickers(false).let { r ->
                if (r.isSuccessful) {
                    val result = r.body() as List<Ticker>
                    tickers = TickerHelper.sort(TickerHelper.filterFiat(result))
                    tickers.map { it.koreanName }.forEach(items::add)
                }
            }
        }
    }

    private fun initializeButton() {
        binding.startButton.setOnClickListener {
            if (!binding.coinMax.text.isNullOrEmpty() &&
                !binding.coinMin.text.isNullOrEmpty() &&
                binding.coinSpinner.text.toString().isNotEmpty()
            ) {
                Intent(this@MainActivity, UpbitPriceAlarmService::class.java).run {
                    this.action = Actions.START_SERVICE
                    this.putExtra(
                        "ticker",
                        tickers.find { it.koreanName == binding.coinSpinner.text.toString() }?.market.toString()
                    )
                    this.putExtra("ticker_name", binding.coinSpinner.text.toString())
                    this.putExtra("max", binding.coinMax.text.toString().toFloat())
                    this.putExtra("min", binding.coinMin.text.toString().toFloat())
                    startService(this)
                }
            } else {
                Toast.makeText(applicationContext, getString(R.string.alert), Toast.LENGTH_LONG)
                    .show()
            }
        }
        binding.stopButton.setOnClickListener {
            alarmHelper.stopAlarm()
            Intent(this@MainActivity, UpbitPriceAlarmService::class.java).run {
                this.action = Actions.STOP_SERVICE
                startService(this)
            }
        }
    }
}
