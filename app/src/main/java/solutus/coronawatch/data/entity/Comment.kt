package solutus.coronawatch.data.entity

data class Comment (
    val  user : String,
    val post : Post,
    var text : String?
)