package com.nguyennhatminh614.motobikedriverlicenseapp.utils

import android.content.Context
import androidx.room.Room
import com.nguyennhatminh614.motobikedriverlicenseapp.data.repository.local.MotorbikeAppDatabase

fun provideDatabase(context: Context) =
    Room.databaseBuilder(
        context,
        MotorbikeAppDatabase::class.java,
        MotorbikeAppDatabase.DATABASE_NAME,
    ).build()
