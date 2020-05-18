package solutus.coronawatch.data.reposetory.abstraction

import retrofit2.Response
import solutus.coronawatch.data.internal.GetDataFromApiException

abstract class SafeApiRequest {

    suspend fun <T : Any> apiRequest(call : suspend () -> Response<T>) : T {
        val response = call.invoke()

        if( response.isSuccessful){
            return response.body()!!
        }
        else {
            throw GetDataFromApiException(response.code().toString())
        }
    }
}