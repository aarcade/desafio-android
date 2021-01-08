package bt.com.desafioana.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import bt.com.desafioana.modelo.PullRequest
import bt.com.desafioana.webservices.InicializadorRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PullViewModel : ViewModel() {
    val liveDataPullSucesso: MutableLiveData<List<PullRequest>> = MutableLiveData()
    val liveDataPullErro = MutableLiveData<Any>()

    fun getPullRequests(owner: String, repositorio: String) {
        val clientPull by lazy { InicializadorRetrofit.init() }
        val call = clientPull.getPullRequests(owner, repositorio)
        call.enqueue(object : Callback<List<PullRequest>> {
            override fun onFailure(call: Call<List<PullRequest>>, t: Throwable) {
                Log.d("Erro de chamada", t.message.toString())
                liveDataPullErro.postValue(t)

            }

            override fun onResponse(
                call: Call<List<PullRequest>>,
                response: Response<List<PullRequest>>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        liveDataPullSucesso.postValue(it)

                    }
                }
            }

        })
    }


}