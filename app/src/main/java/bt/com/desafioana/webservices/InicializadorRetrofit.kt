package bt.com.desafioana.webservices

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object InicializadorRetrofit {

   fun initRepositorio(): RepositorioService {
         return Retrofit.Builder()
           .baseUrl("https://api.github.com/")
           .addConverterFactory(GsonConverterFactory.create())
           .build()
             .create<RepositorioService>(
                 RepositorioService::class.java)
   }

    fun initPull(): PullService {
        return Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create<PullService>(
                PullService::class.java)
    }

}