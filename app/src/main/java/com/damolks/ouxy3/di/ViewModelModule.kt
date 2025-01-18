package com.damolks.ouxy3.di

import com.damolks.ouxy3.ui.main.marketplace.MarketplaceViewModel
import com.damolks.ouxy3.ui.main.settings.SettingsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MarketplaceViewModel(get()) }
    viewModel { SettingsViewModel(get()) }
}