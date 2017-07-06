package androidfanatic.ktodo.main

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import androidfanatic.ktodo.R
import androidfanatic.ktodo.add.AddActivity
import androidfanatic.ktodo.base.MVPActivity
import androidfanatic.ktodo.models.Todo
import kotlinx.android.synthetic.main.activity_main.*

// main activity
class MainActivity(override val presenter: MainPresenter = MainPresenter()) : MVPActivity<MainView, MainPresenter>(), MainView {

    val todoAdapter: TodoAdapter by lazy { TodoAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        todoList.layoutManager = LinearLayoutManager(applicationContext)
        todoList.adapter = todoAdapter

        addBtn?.setOnClickListener { _ ->
            startActivity(Intent(this, AddActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.fetchList()
    }

    override fun updateItems(items: List<Todo>){
        todoAdapter.items = items
        todoAdapter.notifyDataSetChanged()
    }
}

