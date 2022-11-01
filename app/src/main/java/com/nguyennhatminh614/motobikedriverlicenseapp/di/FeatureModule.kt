package com.nguyennhatminh614.motobikedriverlicenseapp.di

import com.nguyennhatminh614.motobikedriverlicenseapp.data.repository.ExamRepository
import com.nguyennhatminh614.motobikedriverlicenseapp.data.repository.IExamDataSource
import com.nguyennhatminh614.motobikedriverlicenseapp.data.repository.IStudyDataSource
import com.nguyennhatminh614.motobikedriverlicenseapp.data.repository.ITipsHighScoreDataSource
import com.nguyennhatminh614.motobikedriverlicenseapp.data.repository.IWrongAnswerDataSource
import com.nguyennhatminh614.motobikedriverlicenseapp.data.repository.StudyRepository
import com.nguyennhatminh614.motobikedriverlicenseapp.data.repository.TipsHighScoreRepository
import com.nguyennhatminh614.motobikedriverlicenseapp.data.repository.WrongAnswerRepository
import com.nguyennhatminh614.motobikedriverlicenseapp.data.repository.local.exam.LocalExamDataSource
import com.nguyennhatminh614.motobikedriverlicenseapp.data.repository.local.study.StudyLocalDataSource
import com.nguyennhatminh614.motobikedriverlicenseapp.data.repository.local.wronganswer.WrongAnswerLocalDataSource
import com.nguyennhatminh614.motobikedriverlicenseapp.data.repository.remote.exam.RemoteExamDataSource
import com.nguyennhatminh614.motobikedriverlicenseapp.data.repository.remote.study.StudyRemoteDataSource
import com.nguyennhatminh614.motobikedriverlicenseapp.data.repository.remote.tipshighscore.RemoteTipsHighScoreDataSource
import com.nguyennhatminh614.motobikedriverlicenseapp.data.repository.remote.wronganswer.WrongAnswerRemoteDataSource
import org.koin.dsl.module

val tipsHighScoreModule = module {
    single<ITipsHighScoreDataSource.Remote> { RemoteTipsHighScoreDataSource(get()) }
    single { TipsHighScoreRepository(get()) }
}

val examModule = module {
    single<IExamDataSource.Local> { LocalExamDataSource(get()) }
    single<IExamDataSource.Remote> { RemoteExamDataSource(get()) }
    single { ExamRepository(get(), get()) }
}

val studyModule = module {
    single<IStudyDataSource.Local> { StudyLocalDataSource(get()) }
    single<IStudyDataSource.Remote> { StudyRemoteDataSource(get()) }
    single { StudyRepository(get(), get()) }
}

val wrongAnswerModule = module {
    single<IWrongAnswerDataSource.Local> { WrongAnswerLocalDataSource(get()) }
    single<IWrongAnswerDataSource.Remote> { WrongAnswerRemoteDataSource(get()) }
    single { WrongAnswerRepository(get(), get()) }
}
