package solutus.coronawatch.data.db.entity

data class Article(
    override var id: String,
    override var publisher: User,
    override var comments: List<Comment>?,
    override var title: String,
    override var url: String?,//text url
    var articleContent: String?//file a changé plus tard

) :
    Publication(
        id,
        publisher,
        title,
        url,
        comments
    )