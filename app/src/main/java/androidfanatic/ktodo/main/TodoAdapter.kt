package androidfanatic.ktodo.main

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidfanatic.ktodo.models.Todo
import androidfanatic.ktodo.R

// adapter
class TodoAdapter(var items: List<Todo> = emptyList()) : RecyclerView.Adapter<TodoVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            = TodoVH(LayoutInflater.from(parent.context).inflate(R.layout.todo_item, null))

    override fun onBindViewHolder(holder: TodoVH, position: Int) =
            holder.bind(items[position])

    override fun getItemCount() = items.size

}