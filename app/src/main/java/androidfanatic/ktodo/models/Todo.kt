package androidfanatic.ktodo.models

import com.orm.SugarRecord

// model
data class Todo(val title: String = "") : SugarRecord()