package androidfanatic.ktodo.base

import androidfanatic.ktodo.BuildConfig
import com.orm.SugarApp
import timber.log.Timber

// main app
class MainApp : SugarApp() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}