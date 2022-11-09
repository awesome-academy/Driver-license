package com.nguyennhatminh614.motobikedriverlicenseapp.utils

import android.os.CountDownTimer
import com.nguyennhatminh614.motobikedriverlicenseapp.utils.constant.AppConstant
import com.nguyennhatminh614.motobikedriverlicenseapp.utils.extensions.toDateTimeMMSS

object CountDownInstance {
    private const val REQUESTED_TICK_INTERVAL = 1000L
    private var countDownTimer: CountDownTimer? = null
    private var currentTimeStamp = AppConstant.EXAM_TEST_FULL_TIME

    val CurrentTimeStamp: Long
        get() = currentTimeStamp

    val CurrentTime: String
        get() = currentTimeStamp.toDateTimeMMSS()

    fun startCountDownFrom(
        timestamp: Long,
        onTickEvent: () -> Unit,
        onFinishEvent: () -> Unit,
    ) {
        if (countDownTimer == null) {
            countDownTimer = object : CountDownTimer(timestamp, REQUESTED_TICK_INTERVAL) {
                override fun onTick(p0: Long) {
                    currentTimeStamp = p0
                    onTickEvent()
                }

                override fun onFinish() {
                    onFinishEvent()
                }
            }

            countDownTimer?.start()
        }
    }

    fun cancelCountDown() {
        countDownTimer?.cancel()
        countDownTimer = null
    }
}
