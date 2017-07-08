package androidfanatic.ktodo.base

// presenter
abstract class MVPPresenter<V: MVPView> {

    protected var view: V? = null

    fun attach(view: V) {
        this.view = view
    }

    fun detach(view: V) {
        this.view = null
    }
}

