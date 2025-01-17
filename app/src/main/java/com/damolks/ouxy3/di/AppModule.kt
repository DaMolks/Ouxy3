package com.damolks.ouxy3.di

import com.damolks.ouxy3.core.session.SessionManager
import org.koin.dsl.module

val appModule = module {
    single { SessionManager(get()) }
}