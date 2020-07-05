package solutus.coronawatch.data.entity

data class Comment(

    override val id: String,
    override val publisher: AppUser,
    var content: String
) :
    Publication(
        id,
        publisher

    )



