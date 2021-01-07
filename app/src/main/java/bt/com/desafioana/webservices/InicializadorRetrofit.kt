package bt.com.desafioana.webservices

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object InicializadorRetrofit {
    fun initRepositorio(): Interfaces.RepositorioService {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS)
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
        return Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create<Interfaces.RepositorioService>(
                Interfaces.RepositorioService::class.java
            )
    }

    fun initPull(): Interfaces.PullService {
        return Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create<Interfaces.PullService>(
                Interfaces.PullService::class.java
            )
    }

}