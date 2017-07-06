package androidfanatic.ktodo.base

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import androidfanatic.ktodo.BuildConfig
import com.orm.SugarApp
import timber.log.Timber

// main app
class BaseApp : SugarApp() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}

// view
abstract class MVPActivity<in V: MVPView, out P: MVPPresenter<V>> :AppCompatActivity(), MVPView {

    protected abstract val presenter: P

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.attach(this as V)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detach(this as V)
    }

    override fun toast(msg: String) {
        Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()
    }
}

// view interface
interface MVPView {
    fun getApplicationContext(): Context
    fun toast(msg: String)
}

// presenter
interface MVPPresenter<in V: MVPView> {
    fun attach(view: V)
    fun detach(view: V)
}

