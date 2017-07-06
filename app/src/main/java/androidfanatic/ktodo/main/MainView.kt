package androidfanatic.ktodo.main

import androidfanatic.ktodo.base.MVPView
import androidfanatic.ktodo.models.Todo

interface MainView: MVPView {
    fun updateItems(items: List<Todo>)
}