package bt.com.desafioana.webservices

import bt.com.desafioana.modelo.Repositorio
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path


class PullRequestService(repositorio: Retrofit) {
    val pullRequestService: PullRequestService
        get() = InicializadorRetrofit.retrofity.create<PullRequestService>(PullRequestService::class.java)

    interface RepositorioService {
        @GET("repos/{owner}/{repositorio}/pulls")
        fun listaPullRequests(
            @Path("owner") owner: String,
            @Path("repositorio") repositorio: String
        ): Callback<List<Repositorio>>
    }
}