package com.damolks.ouxy3.di

import androidx.room.Room
import com.damolks.ouxy3.data.database.OuxyDatabase
import com.damolks.ouxy3.data.repository.PreferencesRepository
import com.damolks.ouxy3.data.repository.TechnicianRepository
import com.damolks.ouxy3.ui.main.MainViewModel
import com.damolks.ouxy3.ui.onboarding.OnboardingViewModel
import com.damolks.ouxy3.ui.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    // Database
    single {
        Room.databaseBuilder(
            get(),
            OuxyDatabase::class.java,
            "ouxy.db"
        ).build()
    }

    // DAOs
    single { get<OuxyDatabase>().technicianDao() }
    single { get<OuxyDatabase>().siteDao() }

    // Repositories
    single { PreferencesRepository(get()) }
    single { TechnicianRepository(get()) }

    // ViewModels
    viewModel { SplashViewModel(get()) }
    viewModel { OnboardingViewModel(get(), get()) }
    viewModel { MainViewModel(get()) }
}