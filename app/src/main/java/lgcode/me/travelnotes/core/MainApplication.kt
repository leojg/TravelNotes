package lgcode.me.travelnotes.core

import android.app.Application
import lgcode.me.travelnotes.core.di.appModule
import org.koin.android.ext.android.startKoin

class MainApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(appModule))
    }
}