package com.damolks.ouxy3.di

import com.damolks.ouxy3.data.database.OuxyDatabase
import com.damolks.ouxy3.data.repository.SiteRepository
import com.damolks.ouxy3.data.repository.TechnicianRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single { OuxyDatabase.getInstance(androidContext()) }
    single { get<OuxyDatabase>().technicianDao() }
    single { get<OuxyDatabase>().siteDao() }
    
    single { TechnicianRepository(get()) }
    single { SiteRepository(get()) }
}