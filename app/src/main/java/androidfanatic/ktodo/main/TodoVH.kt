package androidfanatic.ktodo.main

import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.support.v7.widget.RecyclerView
import android.view.View
import androidfanatic.ktodo.model.Todo
import androidfanatic.ktodo.util.RandomColorGenerator
import kotlinx.android.synthetic.main.todo_item.view.*

// viewholder
class TodoVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(todo: Todo) {
        if (todo.title.isNotEmpty()) {
            itemView.itemTodoTitle.text = todo.title
            itemView.itemTodoTitleChar.text = todo.title[0].toString()
            itemView.itemTodoTitleChar.background.colorFilter = PorterDuffColorFilter(RandomColorGenerator(todo.title[0]), PorterDuff.Mode.MULTIPLY)
        }
    }
}