package androidfanatic.ktodo.pref

import android.os.Bundle
import androidfanatic.ktodo.R
import androidfanatic.ktodo.base.MVPActivity
import com.u1aryz.android.colorpicker.ColorPreferenceFragmentCompat

// pref activity
class PrefActivity(override val presenter: PrefPresenter = PrefPresenter()) :
        MVPActivity<PrefView, PrefPresenter>(), PrefView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pref)
        supportFragmentManager
                .beginTransaction()
                .replace(android.R.id.content, PrefFragment())
                .commit()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

}

class PrefFragment:ColorPreferenceFragmentCompat(){
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.main_pref)
    }
}