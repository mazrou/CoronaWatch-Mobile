package solutus.coronawatch.data.db.entity


data class Comment (
    var publisher : User,
    var text : String?
)