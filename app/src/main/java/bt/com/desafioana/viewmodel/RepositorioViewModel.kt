package bt.com.desafioana.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import bt.com.desafioana.modelo.Repos
import bt.com.desafioana.modelo.Repositorio
import bt.com.desafioana.webservices.InicializadorRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepositorioViewModel : ViewModel() {
    private val clientRepositorio by lazy { InicializadorRetrofit.init() }
    val liveDataRepositorioSucesso: MutableLiveData<List<Repositorio>> = MutableLiveData()
    val liveDataRepositorioErro = MutableLiveData<Any>()
    fun getRepositorio(pagina: Int) {

        clientRepositorio.reposList(pagina).enqueue(object : Callback<Repos> {
            override fun onResponse(call: Call<Repos>, response: Response<Repos>) {
                if (response.isSuccessful) {
                    response.body()?.let {

                        liveDataRepositorioSucesso.postValue(it.items)
                    }

                }
            }

            override fun onFailure(call: Call<Repos>, t: Throwable) {
                Log.d("Erro de chamada", t.message.toString())
                liveDataRepositorioErro.postValue(t)


            }
        })
    }
}