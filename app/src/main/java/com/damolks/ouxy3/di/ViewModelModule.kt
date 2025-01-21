package com.damolks.ouxy3.di

import com.damolks.ouxy3.ui.main.home.HomeViewModel
import com.damolks.ouxy3.ui.main.marketplace.MarketplaceViewModel
import com.damolks.ouxy3.ui.main.settings.SettingsViewModel
import com.damolks.ouxy3.ui.onboarding.signature.SignatureViewModel
import com.damolks.ouxy3.ui.onboarding.sites.SitesSetupViewModel
import com.damolks.ouxy3.ui.onboarding.technician.TechnicianProfileViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    // Onboarding ViewModels
    viewModel { TechnicianProfileViewModel(get()) }
    viewModel { SignatureViewModel(get()) }
    viewModel { SitesSetupViewModel(get()) }

    // Main ViewModels
    viewModel { HomeViewModel(get(), get()) }
    viewModel { MarketplaceViewModel(get()) }
    viewModel { SettingsViewModel(get()) }
}