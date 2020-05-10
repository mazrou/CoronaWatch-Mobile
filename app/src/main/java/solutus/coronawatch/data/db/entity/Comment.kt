package solutus.coronawatch.data.db.entity

data class Comment (
    val  user : String,
    val Video : Video,
    var text : String?
)