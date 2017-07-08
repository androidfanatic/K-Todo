package androidfanatic.ktodo.model

import com.orm.SugarRecord

// model
data class Todo(
        var title: String = "",
        var done: Boolean = false,
        val timeAdded: Long = System.currentTimeMillis()
) : SugarRecord() {
    fun widgetText() = String.format(" %s %s\r\n", if (done) "\u2714" else "\u2718", title)
}