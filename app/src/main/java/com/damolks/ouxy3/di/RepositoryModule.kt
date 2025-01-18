package com.damolks.ouxy3.di

import com.damolks.ouxy3.data.repository.MarketplaceRepository
import com.damolks.ouxy3.data.repository.UserPreferencesRepository
import com.damolks.ouxy3.data.repository.UserPreferencesRepositoryImpl
import com.damolks.ouxy3.data.repository.TechnicianRepository
import com.damolks.ouxy3.data.repository.TechnicianRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoryModule = module {
    single { MarketplaceRepository(get()) }
    single<UserPreferencesRepository> { UserPreferencesRepositoryImpl(androidContext()) }
    single<TechnicianRepository> { TechnicianRepositoryImpl(get()) }
}