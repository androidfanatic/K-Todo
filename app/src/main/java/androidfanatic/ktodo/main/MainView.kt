package androidfanatic.ktodo.main

import androidfanatic.ktodo.base.MVPView
import androidfanatic.ktodo.model.Todo

interface MainView: MVPView {
    fun showEmptyListLayout()
    fun hideEmptyListLayout()
    fun updateItems(items: MutableList<Todo>)
    fun toggleTodoDone(adapterPosition: Int)
    fun deleteTodo(adapterPosition: Int)
}