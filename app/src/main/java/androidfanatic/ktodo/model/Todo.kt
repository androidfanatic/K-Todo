package androidfanatic.ktodo.model

import com.orm.SugarRecord

// model
data class Todo(var title: String = "", var done: Boolean = false) : SugarRecord()


