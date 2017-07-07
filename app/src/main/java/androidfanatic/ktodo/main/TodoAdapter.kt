package androidfanatic.ktodo.main

import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.Typeface
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidfanatic.ktodo.R
import androidfanatic.ktodo.model.Todo
import androidfanatic.ktodo.util.RandomColorGenerator
import kotlinx.android.synthetic.main.todo_item.view.*
import timber.log.Timber

// adapter
class TodoAdapter(var items: List<Todo> = emptyList()) : RecyclerView.Adapter<TodoAdapter.TodoVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            = TodoVH(LayoutInflater.from(parent.context).inflate(R.layout.todo_item, parent, false))

    override fun onBindViewHolder(holder: TodoVH, position: Int) =
            holder.bind(items[position])

    override fun getItemCount() = items.size

    inner class TodoVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {
            Timber.d("init")
            itemView.itemTodoRow.setOnClickListener { toggleDone() }
        }

        private fun toggleDone() {
            val item = items[adapterPosition]
            item.done = !item.done
            item.save()
            notifyItemChanged(adapterPosition)
        }

        fun bind(todo: Todo) {
            if (todo.title.isNotEmpty()) {
                itemView.itemTodoTitle.text = todo.title
                if (todo.done) {
                    itemView.itemTodoTitle.paintFlags = itemView.itemTodoTitle.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                    itemView.itemTodoTitle.setTypeface(null, Typeface.ITALIC)
                } else {
                    itemView.itemTodoTitle.paintFlags = 0
                    itemView.itemTodoTitle.setTypeface(null, Typeface.NORMAL)
                }
                itemView.itemTodoTitleChar.text = todo.title[0].toString()
                itemView.itemTodoTitleChar.background.colorFilter = PorterDuffColorFilter(RandomColorGenerator(todo.title[0]), PorterDuff.Mode.MULTIPLY)
            }
        }
    }
}