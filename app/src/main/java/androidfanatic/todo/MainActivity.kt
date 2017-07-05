package androidfanatic.ktodo

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.orm.SugarApp
import com.orm.SugarRecord
import com.orm.query.Select
import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.todo_item.view.*
import timber.log.Timber

// main app
class App : SugarApp() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}

// main activity
class MainActivity : AppCompatActivity() {

    var todoAdapter: TodoAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        todoList.layoutManager = LinearLayoutManager(applicationContext)
        todoAdapter = TodoAdapter()
        todoList.adapter = todoAdapter

        addBtn?.setOnClickListener { _ -> startActivityForResult(Intent(this, AddActivity::class.java), 999) }
    }

    override fun onResume() {
        super.onResume()
        fetchList()
    }

    fun fetchList() {
        todoAdapter?.apply {
            items = Select.from(Todo::class.java).orderBy("ID DESC").list()
            notifyDataSetChanged()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Timber.d("Result: $requestCode")
        when (requestCode) {
            999 -> fetchList()
        }
    }

}

// add activity
class AddActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        actionBar?.setDisplayHomeAsUpEnabled(true)
        addOkBtn?.setOnClickListener { _ -> addItem() }
    }

    fun addItem() {
        addTodoTitle?.apply {
            if (text.length > 0) {
                Todo(text.toString()).save()
                Timber.d("Finished")
                finish()
            } else {
                error("Add a todo text")
            }
        }
    }
}

fun AppCompatActivity.toast(msg: String) {
    Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show();
}

// model
data class Todo(val title: String = "") : SugarRecord()

// adapter
class TodoAdapter(var items: List<Todo> = emptyList()) : RecyclerView.Adapter<TodoVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            = TodoVH(LayoutInflater.from(parent.context).inflate(R.layout.todo_item, null))

    override fun onBindViewHolder(holder: TodoVH, position: Int) =
            holder.bind(items[position])

    override fun getItemCount() = items.size

}

// viewholder
class TodoVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(todo: Todo) {
        if (todo.title.length > 0) {
            itemView.itemTodoTitle.text = todo.title
//            itemView.itemTodoTitleChar.text = todo.title[0].toString()
        }
    }
}