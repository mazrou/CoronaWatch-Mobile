package solutus.coronawatch.data.reposetory

import solutus.coronawatch.data.db.entity.User
import solutus.coronawatch.data.network.CoronaWatchApi
import solutus.coronawatch.data.network.SafeApiReaquest

class UserRepository ( private val coronaWatchApi: CoronaWatchApi
) : SafeApiReaquest() {
    companion object{
        val user : User = getUserAuthentify()
        //suspend fun getUserAuthentify() =  apiRequest{ coronaWatchApigetUserAuthentify }
        private fun getUserAuthentify(): User {
            //TODO: a changer plus tard : get user connecté
            return User(1,"ourdjini2020","ga_ourdjini@esi.dz","Aymen","ourdjini","04-12-1998","https://s3.ca-central-1.amazonaws.com/codingwithmitch/media/VideoPlayerRecyclerView/Sending+Data+to+a+New+Activity+with+Intent+Extras.png")
        }
    }

}