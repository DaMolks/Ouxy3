package com.damolks.ouxy3.di

import com.damolks.ouxy3.data.local.OuxyDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single { OuxyDatabase.getInstance(androidContext()) }
    single { get<OuxyDatabase>().technicianDao() }
    single { get<OuxyDatabase>().siteDao() }
}