package solutus.coronawatch.data.db.entity

open class Publication(
    open val id: String,
    open val publisher: User,
    open var comments: List<Comment>?
) {
}