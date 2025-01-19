package com.damolks.ouxy3.di

import com.damolks.ouxy3.ui.main.marketplace.MarketplaceViewModel
import com.damolks.ouxy3.ui.main.settings.SettingsViewModel
import com.damolks.ouxy3.ui.onboarding.signature.SignatureViewModel
import com.damolks.ouxy3.ui.onboarding.technician.TechnicianProfileViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { TechnicianProfileViewModel(get()) }
    viewModel { MarketplaceViewModel(get()) }
    viewModel { SettingsViewModel(get()) }
    viewModel { SignatureViewModel(get()) }
}