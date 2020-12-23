package bt.com.desafioana.webservices

import bt.com.desafioana.modelo.PullRequest
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

class RepositorioService(repositorio: Retrofit) {
    val repositorioService: RepositorioService
        get() = InicializadorRetrofit.retrofity.create<RepositorioService>(RepositorioService::class.java)

    interface RepositorioService {
        @GET("search/repositories?q=language:Java&sort=stars&page=1")
        fun listaRepositorios(@Query("page") page: Int): Callback<List<PullRequest>>
    }


}
