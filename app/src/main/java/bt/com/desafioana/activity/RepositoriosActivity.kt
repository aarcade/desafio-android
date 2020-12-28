package bt.com.desafioana.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import bt.com.desafioana.adapter.RepositorioAdapter
import bt.com.desafioana.databinding.ActivityRepositorioBinding
import bt.com.desafioana.modelo.Repos
import bt.com.desafioana.modelo.Repositorio
import bt.com.desafioana.webservices.InicializadorRetrofit.initRepositorio
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepositoriosActivity : AppCompatActivity(), RepositorioAdapter.RecyclerViewClickListener {
    private val list = ArrayList<Repositorio>()
    private val adapter = RepositorioAdapter(list, this)


    private lateinit var binding: ActivityRepositorioBinding

    private val client by lazy { initRepositorio() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRepositorioBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.repositoryRecycler.adapter = adapter
        binding.repositoryRecycler.layoutManager = LinearLayoutManager(this)
        binding.repositoryRecycler.setHasFixedSize(true)
        setSupportActionBar(binding.toolBar)
        supportActionBar?.setDisplayShowHomeEnabled(true)


        client.reposList().enqueue(object : Callback<Repos>{
            override fun onResponse(call: Call<Repos>, response: Response<Repos>) {
                if (response.isSuccessful){
                    response.body()?.let {
                        binding.repositoryRecycler.adapter = RepositorioAdapter(it.items, this@RepositoriosActivity)
                    }
                }
            }

            override fun onFailure(call: Call<Repos>, t: Throwable) {
                Log.d("Erro de chamada", t.message.toString())
                Toast.makeText(this@RepositoriosActivity, t.message, Toast.LENGTH_LONG).show()
            }

        })


    }


    object Constants {
        const val owner = "OWNER"
        const val repositorio = "REPOSITORIO"
    }


    override fun onRecyclerViewItemClick(view: View, repo: Repositorio) {
        val intencao = Intent(this@RepositoriosActivity, PullActivity::class.java)
        startActivity(intencao)
    }


}




















