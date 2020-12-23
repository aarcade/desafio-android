package bt.com.desafioana.activity.repositorio

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import bt.com.desafioana.R
import bt.com.desafioana.activity.pull.PullActivity
import bt.com.desafioana.adapter.RepositorioAdapter
import bt.com.desafioana.databinding.ActivityRepositorioBinding
import bt.com.desafioana.modelo.Repositorio
import bt.com.desafioana.viewmodel.RepositorioViewModel
import bt.com.desafioana.viewmodel.ViewModelFactory
import bt.com.desafioana.webservices.InicializadorRetrofit
import bt.com.desafioana.webservices.RepositorioService


class RepositoriosActivity : AppCompatActivity(), RepositorioAdapter.OnItemClickListener{
    var pagina =1
    private val repositorioList=ArrayList<Repositorio>()
    var linearLayoutManager = LinearLayoutManager(this)
    val adapterRepository = RepositorioAdapter(repositorioList, this)
    private val repositorioViewModel: RepositorioViewModel by lazy {

    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityRepositorioBinding.inflate(layoutInflater)
        setContentView(binding.root)
        repositorioViewModel.listaRepositorio(pagina)

        val repositorioService = InicializadorRetrofit.retrofit.create(RepositorioService::class.java)





    }

    override fun onItemClick(position: Int) {
        val intencao = Intent(this, PullActivity::class.java)
        startActivity(intencao)
        adapter.notifyItemChanged(position)

    }

    private fun generateDummyList(size: Int): List<Repositorio> {

        val list = ArrayList<Repositorio>()
        for (i in 0 until size) {
            val drawable = when (i % 3) {
                0 -> R.drawable.ic_usuario
                else -> R.drawable.ic_usuario
            }
            val item = Repositorio(drawable, "Lu", "Java", "blabla", "Luana", 9, 10)
            list += item
        }
        return list

        val recycler_view = findViewById<RecyclerView>(R.id.repository_recycler)
        recycler_view?.adapter = adapter
        recycler_view?.layoutManager = LinearLayoutManager(this)
        recycler_view?.setHasFixedSize(true)
    }

}
