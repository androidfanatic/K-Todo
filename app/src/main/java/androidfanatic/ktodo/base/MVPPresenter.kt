package androidfanatic.ktodo.base

// presenter
interface MVPPresenter<in V: MVPView> {
    fun attach(view: V)
    fun detach(view: V)
}

