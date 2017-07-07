package androidfanatic.ktodo.base

import android.content.Context

// view interface
interface MVPView {
    fun getApplicationContext(): Context
    fun toast(msg: String)
}