package bt.com.desafioana.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import bt.com.desafioana.adapter.RepositorioAdapter
import bt.com.desafioana.databinding.ActivityRepositorioBinding
import bt.com.desafioana.viewmodel.RepositorioViewModel

class RepositoriosActivity : AppCompatActivity(), RepositorioAdapter.RecyclerViewClickListener {
    private val adapterRepositorio = RepositorioAdapter(ArrayList(), this)

    private lateinit var binding: ActivityRepositorioBinding
    private lateinit var viewModel: RepositorioViewModel
    private var pagina = 1

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
        sucessoRepositorio()
        erroRepositorio()
    }

    private fun erroRepositorio() {
        viewModel.liveDataRepositorioErro.observe(this, Observer {
            Toast.makeText(this, "Erro!", Toast.LENGTH_SHORT).show()
        })
    }

    private fun sucessoRepositorio() {
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
