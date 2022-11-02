package com.nguyennhatminh614.motobikedriverlicenseapp.di

import android.content.Context
import androidx.room.Room
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.nguyennhatminh614.motobikedriverlicenseapp.data.repository.local.MotorbikeAppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single { provideDatabase(androidContext()) }

    single { get<MotorbikeAppDatabase>().getExamDao() }
    single { get<MotorbikeAppDatabase>().getQuestionDao() }
    single { get<MotorbikeAppDatabase>().getStudyDao() }
    single { get<MotorbikeAppDatabase>().getWrongAnswerDao() }
}

val apiModule = module {
    single { Firebase.firestore }
}

fun provideDatabase(context: Context): MotorbikeAppDatabase =
    Room.databaseBuilder(
        context,
        MotorbikeAppDatabase::class.java,
        MotorbikeAppDatabase.DATABASE_NAME,
    ).allowMainThreadQueries().build()
