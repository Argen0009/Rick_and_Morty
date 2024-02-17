package mbk.io.homework2

import org.koin.core.context.startKoin
import android.app.Application
import mbk.io.homework2.di.CartoonModule
import org.koin.android.ext.koin.androidContext


class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(CartoonModule)
        }
    }
}