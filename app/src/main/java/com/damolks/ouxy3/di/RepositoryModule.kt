package com.damolks.ouxy3.di

import com.damolks.ouxy3.data.repository.*
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoryModule = module {
    single { MarketplaceRepository(get()) }
    single<UserPreferencesRepository> { UserPreferencesRepositoryImpl(androidContext()) }
    single<TechnicianRepository> { TechnicianRepositoryImpl(get()) }
    single<SiteRepository> { SiteRepositoryImpl(get()) }
}