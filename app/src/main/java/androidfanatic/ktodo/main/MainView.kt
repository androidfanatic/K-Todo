package androidfanatic.ktodo.main

import androidfanatic.ktodo.base.MVPView
import androidfanatic.ktodo.model.Todo

interface MainView: MVPView {
    fun updateItems(items: List<Todo>)
}