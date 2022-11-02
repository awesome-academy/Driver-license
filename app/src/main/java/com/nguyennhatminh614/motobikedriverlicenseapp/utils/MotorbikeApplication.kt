package com.nguyennhatminh614.motobikedriverlicenseapp.utils

import android.app.Application
import com.nguyennhatminh614.motobikedriverlicenseapp.di.apiModule
import com.nguyennhatminh614.motobikedriverlicenseapp.di.databaseModule
import com.nguyennhatminh614.motobikedriverlicenseapp.di.examModule
import com.nguyennhatminh614.motobikedriverlicenseapp.di.studyModule
import com.nguyennhatminh614.motobikedriverlicenseapp.di.tipsHighScoreModule
import com.nguyennhatminh614.motobikedriverlicenseapp.di.viewModelModule
import com.nguyennhatminh614.motobikedriverlicenseapp.di.wrongAnswerModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MotorbikeApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MotorbikeApplication)

            modules(
                databaseModule,
                apiModule,
                tipsHighScoreModule,
                examModule,
                viewModelModule,
                studyModule,
                wrongAnswerModule,
            )
        }
    }
}
