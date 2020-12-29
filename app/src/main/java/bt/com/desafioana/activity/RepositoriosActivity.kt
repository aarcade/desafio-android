package bt.com.desafioana.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import bt.com.desafioana.adapter.RepositorioAdapter
import bt.com.desafioana.databinding.ActivityRepositorioBinding
import bt.com.desafioana.viewmodel.RepositorioViewModel
import bt.com.desafioana.webservices.InicializadorRetrofit.initRepositorio

class RepositoriosActivity : AppCompatActivity(), RepositorioAdapter.RecyclerViewClickListener {
    private val adapterRepositorio = RepositorioAdapter(ArrayList(), this)

    private lateinit var binding: ActivityRepositorioBinding
    private lateinit var viewModel: RepositorioViewModel
    private var pagina = 1
    private var isLoading = false

    private val client by lazy { initRepositorio() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inicializador()

    }


    private fun inicializador() {
        binding = ActivityRepositorioBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.repositoryRecycler.adapter = adapterRepositorio
        binding.repositoryRecycler.layoutManager = LinearLayoutManager(this)
        binding.repositoryRecycler.setHasFixedSize(true)
        setSupportActionBar(binding.toolBar)
        supportActionBar?.setDisplayShowHomeEnabled(true)


        getRepositorio(pagina)

    }

    private fun getRepositorio(pagina: Int) {

          viewModel = ViewModelProvider(this).get(RepositorioViewModel::class.java)
          viewModel.liveDataRepositorioSucesso.observe(this, Observer {
              adapterRepositorio.repos.addAll(it)
              adapterRepositorio.notifyDataSetChanged()

          })

        viewModel.getRepositorio(pagina)
    }

    object Constants {
        const val owner = "OWNER"
        const val repositorio = "REPOSITORIO"
    }


    override fun onRecyclerViewItemClick(position: Int) {
        val intencao = Intent(this@RepositoriosActivity, PullActivity::class.java)
        intencao.putExtra(Constants.owner, adapterRepositorio.repos[position].owner.login)
        intencao.putExtra(Constants.repositorio, adapterRepositorio.repos[position].name)
        startActivity(intencao)
    }


}




















