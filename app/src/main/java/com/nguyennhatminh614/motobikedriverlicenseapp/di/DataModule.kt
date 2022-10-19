package com.nguyennhatminh614.motobikedriverlicenseapp.di

import com.nguyennhatminh614.motobikedriverlicenseapp.data.repository.ExamRepository
import com.nguyennhatminh614.motobikedriverlicenseapp.data.repository.IExamDataSource
import com.nguyennhatminh614.motobikedriverlicenseapp.data.repository.ITipsHighScoreDataSource
import com.nguyennhatminh614.motobikedriverlicenseapp.data.repository.TipsHighScoreRepository
import com.nguyennhatminh614.motobikedriverlicenseapp.data.repository.local.exam.LocalExamDataSource
import com.nguyennhatminh614.motobikedriverlicenseapp.data.repository.remote.exam.RemoteExamDataSource
import com.nguyennhatminh614.motobikedriverlicenseapp.data.repository.remote.tipshighscore.RemoteTipsHighScoreDataSource
import org.koin.dsl.module

val tipsHighScoreModule = module {
    single<ITipsHighScoreDataSource.Remote> { RemoteTipsHighScoreDataSource() }
    single { TipsHighScoreRepository(get()) }
}

val examModule = module {
    single<IExamDataSource.Local> { LocalExamDataSource() }
    single<IExamDataSource.Remote> { RemoteExamDataSource() }
    single { ExamRepository(get(), get()) }
}
