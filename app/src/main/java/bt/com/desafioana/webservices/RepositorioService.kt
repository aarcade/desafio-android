package bt.com.desafioana.webservices

import bt.com.desafioana.modelo.Repos
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RepositorioService {
    @GET("search/repositories?q=language:Java&sort=stars")
    fun reposList(@Query("page") page: Int): Call<Repos>
}
