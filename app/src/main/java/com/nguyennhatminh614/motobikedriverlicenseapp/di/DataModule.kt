package com.nguyennhatminh614.motobikedriverlicenseapp.di

import android.content.Context
import androidx.room.Room
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.nguyennhatminh614.motobikedriverlicenseapp.data.repository.ExamRepository
import com.nguyennhatminh614.motobikedriverlicenseapp.data.repository.IExamDataSource
import com.nguyennhatminh614.motobikedriverlicenseapp.data.repository.ITipsHighScoreDataSource
import com.nguyennhatminh614.motobikedriverlicenseapp.data.repository.TipsHighScoreRepository
import com.nguyennhatminh614.motobikedriverlicenseapp.data.repository.local.MotorbikeAppDatabase
import com.nguyennhatminh614.motobikedriverlicenseapp.data.repository.local.exam.ExamDao
import com.nguyennhatminh614.motobikedriverlicenseapp.data.repository.local.exam.LocalExamDataSource
import com.nguyennhatminh614.motobikedriverlicenseapp.data.repository.remote.exam.RemoteExamDataSource
import com.nguyennhatminh614.motobikedriverlicenseapp.data.repository.remote.tipshighscore.RemoteTipsHighScoreDataSource
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val tipsHighScoreModule = module {
    single<ITipsHighScoreDataSource.Remote> { RemoteTipsHighScoreDataSource(get()) }
    single { TipsHighScoreRepository(get()) }
}
val databaseModule = module {
    single<ExamDao> {
        Room.databaseBuilder(
            androidContext(),
            MotorbikeAppDatabase::class.java,
            MotorbikeAppDatabase.DATABASE_NAME,
        ).build().getExamDao()
    }
}

val apiModule = module {
    single { Firebase.firestore }
}

val examModule = module {
    single<IExamDataSource.Local> { LocalExamDataSource(get()) }
    single<IExamDataSource.Remote> { RemoteExamDataSource(get()) }
    single { ExamRepository(get(), get()) }
}

