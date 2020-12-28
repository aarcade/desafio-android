package bt.com.desafioana.webservices

import bt.com.desafioana.modelo.Repos
import retrofit2.Call
import retrofit2.http.GET

interface RepositorioService {
    @GET("search/repositories?q=language:Java&sort=stars&")
    fun reposList(): Call<Repos>
}
