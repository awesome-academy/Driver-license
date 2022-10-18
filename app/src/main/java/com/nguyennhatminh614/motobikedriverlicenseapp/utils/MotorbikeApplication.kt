package com.nguyennhatminh614.motobikedriverlicenseapp.utils

import android.app.Application
import com.nguyennhatminh614.motobikedriverlicenseapp.data.di.examModule
import com.nguyennhatminh614.motobikedriverlicenseapp.data.di.tipsHighScoreModule
import com.nguyennhatminh614.motobikedriverlicenseapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MotorbikeApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MotorbikeApplication)

            modules(
                viewModelModule,
                tipsHighScoreModule,
                examModule,
            )
        }
    }
}
