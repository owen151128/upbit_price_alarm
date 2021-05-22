package kr.owens.upa.service

import android.app.Service
import android.content.Intent
import android.os.Bundle
import android.os.IBinder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kr.owens.upa.R
import kr.owens.upa.helper.AlarmHelper
import kr.owens.upa.model.Price
import kr.owens.upa.network.UpbitService
import java.util.concurrent.atomic.AtomicBoolean
import kotlin.math.absoluteValue

class UpbitPriceAlarmService : Service() {

    @Volatile
    private var isServiceEnd = AtomicBoolean(false)

    private lateinit var alarmHelper: AlarmHelper

    companion object {
        const val NOTIFICATION_ID = 5424
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            Actions.START_SERVICE -> startForegroundService(intent.extras)
            Actions.STOP_SERVICE -> stopForegroundService()
        }

        return START_REDELIVER_INTENT
    }

    override fun onCreate() {
        super.onCreate()
        alarmHelper = AlarmHelper.getInstance(null, null)
    }

    override fun onBind(intent: Intent?): IBinder? = null

    private fun startForegroundService(bundle: Bundle?) {
        val notification = UpbitPriceNotification.createNotification(
            this,
            getString(R.string.app_name),
            getString(R.string.app_name)
        )
        startForeground(NOTIFICATION_ID, notification)
        Thread {
            while (!isServiceEnd.get()) {
                observeCurrentPrice(
                    bundle?.getString("ticker").toString(),
                    bundle?.getString("ticker_name").toString(),
                    bundle!!.getFloat("min").absoluteValue,
                    bundle.getFloat("max").absoluteValue
                )
                Thread.sleep(1000)
            }
        }.start()
    }

    private fun observeCurrentPrice(markets: String, name: String, min: Float, max: Float) {
        CoroutineScope(Dispatchers.IO).launch {
            UpbitService.client.getCurrentPrice(markets).let { r ->
                if (r.isSuccessful) {
                    val result = r.body() as List<Price>
                    val price = result[0].tradePrice.toFloat()
                    if (price >= max || price <= min) {
                        alarmHelper.playAlarm()
                    }
                    UpbitPriceNotification.updateNotification(
                        this@UpbitPriceAlarmService,
                        name,
                        "${result[0].tradePrice} KRW"
                    )
                }
            }
        }
    }

    private fun stopForegroundService() {
        alarmHelper.stopAlarm()
        isServiceEnd.set(true)
        Thread.sleep(1000)
        stopForeground(true)
        stopSelf()
    }
}