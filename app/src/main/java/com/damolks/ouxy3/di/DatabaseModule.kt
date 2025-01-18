package com.damolks.ouxy3.di

import android.content.Context
import androidx.room.Room
import com.damolks.ouxy3.data.db.OuxyDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single { provideDatabase(androidContext()) }
    single { provideMarketplaceDao(get()) }
}

private fun provideDatabase(context: Context) =
    Room.databaseBuilder(
        context.applicationContext,
        OuxyDatabase::class.java,
        "ouxy.db"
    ).build()

private fun provideMarketplaceDao(database: OuxyDatabase) = database.marketplaceDao()