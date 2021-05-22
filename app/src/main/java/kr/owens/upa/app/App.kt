package kr.owens.upa.app

import android.app.Application
import android.os.Vibrator
import kr.owens.upa.helper.AlarmHelper

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        AlarmHelper.getInstance(applicationContext, getSystemService(VIBRATOR_SERVICE) as Vibrator)
    }
}