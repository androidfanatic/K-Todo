package androidfanatic.ktodo.main

import androidfanatic.ktodo.base.MVPPresenter
import androidfanatic.ktodo.model.Todo
import com.orm.query.Select

class MainPresenter : MVPPresenter<MainView>() {

    fun fetchList() {
        val items = getTodos()
        if (items.size > 0){
            items.map { it.title = it.title.capitalize() }
            view?.updateItems(items)
            view?.hideEmptyListLayout()
        } else {
            view?.showEmptyListLayout()
        }
    }

    fun getTodos(): List<Todo>
            = Select.from(Todo::class.java).orderBy("ID DESC").list()


}
