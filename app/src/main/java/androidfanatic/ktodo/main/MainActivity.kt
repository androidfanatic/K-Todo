package androidfanatic.ktodo.main

import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import androidfanatic.ktodo.R
import androidfanatic.ktodo.add.AddActivity
import androidfanatic.ktodo.base.MVPActivity
import androidfanatic.ktodo.model.Todo
import kotlinx.android.synthetic.main.activity_main.*

// main activity
class MainActivity(override val presenter: MainPresenter = MainPresenter()) : MVPActivity<MainView, MainPresenter>(), MainView {

    val todoAdapter: TodoAdapter by lazy { TodoAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initTodoList()

        addBtn?.setOnClickListener { startActivity(Intent(this, AddActivity::class.java)) }
    }

    private fun initTodoList() {
        todoList.layoutManager = LinearLayoutManager(applicationContext)
        val divider = DividerItemDecoration(applicationContext, DividerItemDecoration.VERTICAL)
        divider.setDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.todo_divider))
        todoList.addItemDecoration(divider)
        todoList.adapter = todoAdapter
    }

    override fun onResume() {
        super.onResume()
        presenter.fetchList()
    }

    override fun updateItems(items: MutableList<Todo>){
        todoAdapter.items = items
        todoAdapter.notifyDataSetChanged()
    }

    override fun showEmptyListLayout(){
        todoListEmpty.visibility= View.VISIBLE
    }

    override fun hideEmptyListLayout(){
        todoListEmpty.visibility= View.GONE
    }
}
