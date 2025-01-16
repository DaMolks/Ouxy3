package com.damolks.ouxy3

import android.app.Application
import com.damolks.ouxy3.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class OuxyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        
        startKoin {
            androidLogger()
            androidContext(this@OuxyApplication)
            modules(appModule)
        }
    }
}