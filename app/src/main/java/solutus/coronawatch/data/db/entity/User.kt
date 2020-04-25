package solutus.coronawatch.data.db.entity

data class User(
    var id : Int ,
    var userName:String,
    var email:String,
    var firsName : String,
    var lastName : String,
    var birthDate : String,
    var avatar : String
){

}