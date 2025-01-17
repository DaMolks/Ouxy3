package com.damolks.ouxy3.di

import com.damolks.ouxy3.ui.onboarding.signature.SignatureViewModel
import com.damolks.ouxy3.ui.onboarding.sites.SitesSetupViewModel
import com.damolks.ouxy3.ui.onboarding.technician.TechnicianProfileViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { TechnicianProfileViewModel(get()) }
    viewModel { SignatureViewModel(get()) }
    viewModel { SitesSetupViewModel(get()) }
}