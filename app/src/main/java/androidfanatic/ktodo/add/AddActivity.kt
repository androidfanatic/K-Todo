package androidfanatic.ktodo.add

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import androidfanatic.ktodo.R
import androidfanatic.ktodo.model.Todo
import kotlinx.android.synthetic.main.activity_add.*
import timber.log.Timber

// add activity
class AddActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        actionBar?.setDisplayHomeAsUpEnabled(true)
        addOkBtn?.setOnClickListener { addItem() }
    }

    fun addItem() {
        addTodoTitle?.apply {
            if (text.isNotEmpty()) {
                Todo(text.toString()).save()
                text.clear()
                Timber.d("Finished")
                finish()
            } else {
                error = "Add a todo text"
            }
        }
    }
}