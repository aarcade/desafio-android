package bt.com.desafioana.webservices

import bt.com.desafioana.modelo.PullRequest
import bt.com.desafioana.modelo.Repositorio
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query


interface RepositorioService {
    @GET("search/repositories?q=language:Java&sort=stars")
    suspend fun listaRepositorios(@Query("page") page: Int): Response<List<Repositorio>>
}
