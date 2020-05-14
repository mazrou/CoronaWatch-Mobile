package solutus.coronawatch.data.db.entity

open abstract class Publication(
    open var id: String,
    open var publisher: User,
    open var title : String,
    open var url : String?,
    open var comments: List<Comment>?
) {
}