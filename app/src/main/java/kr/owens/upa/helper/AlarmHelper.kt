package kr.owens.upa.helper

import android.content.Context
import android.media.Ringtone
import android.media.RingtoneManager
import android.os.VibrationEffect
import android.os.Vibrator

class AlarmHelper(context: Context?, _vibrator: Vibrator?) {

    companion object {
        @Volatile
        private var instance: AlarmHelper? = null

        @JvmStatic
        fun getInstance(context: Context?, vibrator: Vibrator?): AlarmHelper =
            instance ?: synchronized(this) {
                instance ?: AlarmHelper(context, vibrator).also {
                    instance = it
                }
            }
    }

    private val vibratePattern = longArrayOf(0, 2500, 1000)
    private val amplitudes = intArrayOf(0, 255, 255)
    private var isPlay = false
    private val vibrator = _vibrator
    private val ringtone: Ringtone = RingtoneManager.getRingtone(
        context,
        RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE)
    )

    fun playAlarm() {
        if (!isPlay) {
            vibrator?.vibrate(VibrationEffect.createWaveform(vibratePattern, amplitudes, 1))
            ringtone.play()
            isPlay = true
        }
    }

    fun stopAlarm() {
        isPlay = false
        vibrator?.cancel()
        ringtone.stop()
    }
}