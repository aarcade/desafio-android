package bt.com.desafioana.webservices

import bt.com.desafioana.modelo.PullRequest
import bt.com.desafioana.modelo.Repos
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

class Interfaces {
    interface RepositorioService {
        @GET("search/repositories?q=language:Java&sort=stars")
        fun reposList(@Query("page") page: Int): Call<Repos>
    }

    interface PullService {

        @GET("repos/{owner}/{repositorio}/pulls")
        fun getPullRequests(
            @Path("owner") owner: String,
            @Path("repositorio") repositorio: String
        ): Call<List<PullRequest>>
    }
}

