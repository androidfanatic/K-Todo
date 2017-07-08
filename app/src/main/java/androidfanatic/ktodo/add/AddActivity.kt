package androidfanatic.ktodo.add

import android.os.Bundle
import androidfanatic.ktodo.R
import androidfanatic.ktodo.base.MVPActivity
import androidfanatic.ktodo.base.MVPPresenter
import androidfanatic.ktodo.base.MVPView
import androidfanatic.ktodo.model.Todo
import kotlinx.android.synthetic.main.activity_add.*

// add activity
class AddActivity(override val presenter: AddPresenter = AddPresenter()) : MVPActivity<AddView, AddPresenter>(), AddView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        actionBar?.setDisplayHomeAsUpEnabled(true)
        addOkBtn?.setOnClickListener { addItem() }
    }

    fun addItem() {
        addTodoTitle?.apply {
            if (text.isNotEmpty()) {
                val todo = Todo(text.toString())
                if (addTodoMessage?.text!!.isNotEmpty()){
                    todo.message = addTodoMessage?.text.toString()
                }
                todo.save()
                text.clear()
                finish()
            } else {
                noTitleError()
            }
        }
    }

    override fun noTitleError(){
        addTodoTitle?.error = "Add a todo text"
    }
}

class AddPresenter: MVPPresenter<AddView>() {
    fun saveNewTodo(title: String, message: String = "") {
        Todo(title, message).save()
    }
}

interface AddView:MVPView{
    fun noTitleError()
}