package bt.com.desafioana.webservices

import bt.com.desafioana.modelo.PullRequest
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PullService {

    @GET("repos/{owner}/{repositorio}/pulls")
    fun getPullRequests(@Path("owner") owner: String, @Path("repositorio") repositorio: String): Call<List<PullRequest>>
}