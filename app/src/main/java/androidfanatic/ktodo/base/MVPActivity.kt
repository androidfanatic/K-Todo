package androidfanatic.ktodo.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast

// view
abstract class MVPActivity<in V: MVPView, out P: MVPPresenter<V>> : AppCompatActivity(), MVPView {

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