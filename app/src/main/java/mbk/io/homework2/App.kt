package mbk.io.homework2

import mbk.io.homework2.di.cartoonModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import android.app.Application


class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(cartoonModel)
        }
    }
}