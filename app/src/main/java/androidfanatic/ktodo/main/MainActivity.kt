package androidfanatic.ktodo.main

import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidfanatic.ktodo.R
import androidfanatic.ktodo.add.AddActivity
import androidfanatic.ktodo.base.MVPActivity
import androidfanatic.ktodo.model.Todo
import androidfanatic.ktodo.pref.PrefActivity
import androidfanatic.ktodo.widget.updateWidgets
import kotlinx.android.synthetic.main.activity_main.*

// main activity
class MainActivity(override val presenter: MainPresenter = MainPresenter()) : MVPActivity<MainView, MainPresenter>(), MainView {

    val todoAdapter: TodoAdapter by lazy { TodoAdapter(view = this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initTodoList()
        addBtn?.setOnClickListener { startActivity(Intent(this, AddActivity::class.java)) }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.preferenceMenuItem -> startActivity(Intent(this, PrefActivity::class.java))
        }
        return false
    }

    private fun initTodoList() {
        todoList.layoutManager = LinearLayoutManager(this)
        val divider = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        divider.setDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.todo_divider))
        todoList.addItemDecoration(divider)
        todoList.adapter = todoAdapter
        ItemTouchHelper(TodoItemSwipeListenr()).attachToRecyclerView(todoList)
    }

    inner class TodoItemSwipeListenr : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {

        override fun onMove(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?, target: RecyclerView.ViewHolder?): Boolean {
            return false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder?, direction: Int) {
            when (direction) {
                ItemTouchHelper.RIGHT -> viewHolder?.apply { deleteTodo(adapterPosition) }
            }
        }

    }

    override fun onResume() {
        super.onResume()
        presenter.fetchList()
        updateWidgets(this)
    }

    override fun updateItems(items: MutableList<Todo>) {
        todoAdapter.items = items
        todoAdapter.notifyDataSetChanged()
        updateWidgets(this)
    }

    override fun showEmptyListLayout() {
        todoListEmpty.visibility = View.VISIBLE
    }

    override fun hideEmptyListLayout() {
        todoListEmpty.visibility = View.GONE
    }

    override fun toggleTodoDone(adapterPosition: Int) {
        val item = todoAdapter.items[adapterPosition]
        item.done = !item.done
        item.save()
        todoAdapter.notifyItemChanged(adapterPosition)
        updateWidgets(this)
    }

    override fun deleteTodo(adapterPosition: Int) {
        AlertDialog
                .Builder(this)
                .setTitle("Confirm delete?")
                .setMessage("Delete this task forever? This action can not be reversed.")
                .setPositiveButton("Confirm", { dialog, which ->
                    todoAdapter.items[adapterPosition].delete()
                    updateWidgets(this)
                    todoAdapter.items.removeAt(adapterPosition)
                    if (todoAdapter.items.size == 0) {
                        showEmptyListLayout()
                    }
                    todoAdapter.notifyItemRemoved(adapterPosition)
                })
                .setOnDismissListener {
                    todoAdapter.notifyItemChanged(adapterPosition)
                }
                .setNegativeButton("Cancel", { dialog, which ->
                    dialog.dismiss()
                })
                .show()
    }

}


