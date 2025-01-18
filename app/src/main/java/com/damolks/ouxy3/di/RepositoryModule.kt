package com.damolks.ouxy3.di

import com.damolks.ouxy3.data.repository.MarketplaceRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { MarketplaceRepository(get()) }
}