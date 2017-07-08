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

// adapter
class TodoAdapter(var items: List<Todo> = emptyList()) : RecyclerView.Adapter<TodoAdapter.TodoVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            = TodoVH(LayoutInflater.from(parent.context).inflate(R.layout.todo_item, parent, false))

    override fun onBindViewHolder(holder: TodoVH, position: Int) =
            holder.bind(items[position])

    override fun getItemCount() = items.size

    inner class TodoVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private fun setDone(isDone: Boolean){
            val item = items[adapterPosition]
            if (item.done != isDone){
                item.done = isDone
                item.save()
                notifyItemChanged(adapterPosition)
            }
        }

        private fun toggleMetaRow() {
            if (itemView.itemMetaRow.visibility == View.GONE) {
                itemView.itemMetaRow.visibility = View.VISIBLE
            } else {
                itemView.itemMetaRow.visibility = View.GONE
            }
        }

        fun bind(todo: Todo) {
            if (todo.title.isNotEmpty()) {

                itemView.itemTodoTitle.text = todo.title
                itemView.itemTodoTimeAdded.text = todo.getTimeAddedString()
                itemView.itemTodoMessage.text = todo.message
                itemView.itemMetaRow.visibility = View.GONE

                if (todo.done) {
                    itemView.itemTodoDone.isChecked = true
                    itemView.itemTodoTitle.paintFlags = itemView.itemTodoTitle.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                    itemView.itemTodoTitle.setTypeface(null, Typeface.ITALIC)
                } else {
                    itemView.itemTodoDone.isChecked = false
                    itemView.itemTodoTitle.paintFlags = 0
                    itemView.itemTodoTitle.setTypeface(null, Typeface.NORMAL)
                }

                itemView.itemTodoTitleChar.text = todo.title[0].toString()
                itemView.itemTodoTitleChar.background.colorFilter = PorterDuffColorFilter(RandomColorGenerator(todo.title[0]), PorterDuff.Mode.MULTIPLY)

                itemView.itemTodoRow.setOnClickListener { toggleMetaRow() }
                itemView.itemTodoDone.setOnCheckedChangeListener { _, isChecked -> setDone(isChecked) }
            }
        }
    }
}