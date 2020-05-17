package solutus.coronawatch.data.db.entity

data class Comment (
    val  user : String,
    val post : Post,
    var text : String?
)