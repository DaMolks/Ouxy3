package com.damolks.ouxy3

import android.app.Application
import com.damolks.ouxy3.debug.di.debugModule
import com.damolks.ouxy3.di.appModule
import com.damolks.ouxy3.di.databaseModule
import com.damolks.ouxy3.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class OuxyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@OuxyApplication)
            modules(
                listOf(
                    appModule,
                    databaseModule,
                    viewModelModule,
                    debugModule
                )
            )
        }

        // Initialisation du dossier signatures
        getDir("signatures", MODE_PRIVATE)
    }
}