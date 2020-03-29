package solutus.coronawatch.data.db.entity

data class Comment (
    val  user : User,
    val publication : Publication,
    var text : String?
)