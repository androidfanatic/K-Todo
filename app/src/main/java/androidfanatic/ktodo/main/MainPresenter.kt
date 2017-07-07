package androidfanatic.ktodo.main

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import androidfanatic.ktodo.base.MVPPresenter
import androidfanatic.ktodo.model.Todo
import com.orm.query.Select

class MainPresenter : MVPPresenter<MainView> {

    private var view: MainView? = null

    override fun attach(view: MainView) {
        this.view = view
    }

    override fun detach(view: MainView) {
        this.view = null
    }

    fun fetchList() {
        val items = Select.from(Todo::class.java).orderBy("ID DESC").list()
        items.map { it.title = it.title.capitalize() }
        view?.updateItems(items)
    }

}
