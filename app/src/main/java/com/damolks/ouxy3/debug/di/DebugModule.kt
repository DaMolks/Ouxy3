package com.damolks.ouxy3.debug.di

import com.damolks.ouxy3.debug.ModuleMonitor
import org.koin.dsl.module

val debugModule = module {
    single { ModuleMonitor() }
}