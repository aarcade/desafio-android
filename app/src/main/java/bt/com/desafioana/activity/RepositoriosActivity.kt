package bt.com.desafioana.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import bt.com.desafioana.adapter.RepositorioAdapter
import bt.com.desafioana.databinding.ActivityRepositorioBinding
import bt.com.desafioana.utils.Constants
import bt.com.desafioana.viewmodel.RepositorioViewModel

class RepositoriosActivity : AppCompatActivity(), RepositorioAdapter.RecyclerViewClickListener {
    private val adapterRepositorio = RepositorioAdapter(ArrayList(), this)
    private lateinit var binding: ActivityRepositorioBinding
    private lateinit var viewModel: RepositorioViewModel
    var pagina = 1
    var isLoading = false
    var lastPosition = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RepositorioViewModel::class.java)
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
        getRepositorio()
        onScrollListener()


    }

    private fun onScrollListener() {
        binding.repositoryRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val layoutManager =
                    recyclerView.layoutManager as LinearLayoutManager
                val lastCompleteItem = layoutManager.findLastVisibleItemPosition()

                if (!isLoading) {
                    if (lastCompleteItem == adapterRepositorio.repos.size - 1) {
                        lastPosition = adapterRepositorio.repos.size + 1
                        pagina += 1
                        getRepositorio()
                        binding.progressBar.visibility = View.VISIBLE
                    }
                }


            }
        })
    }


    fun getRepositorio() {
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
            for (i in it) {
                if (i !in adapterRepositorio.repos) {
                    adapterRepositorio.repos.addAll(listOf(i))
                    adapterRepositorio.notifyDataSetChanged()
                    binding.progressBar.visibility = View.GONE

                }
            }


        })
        viewModel.getRepositorio(pagina)

    }

    override fun onRecyclerViewItemClick(position: Int) {
        val intencao = Intent(this@RepositoriosActivity, PullActivity::class.java)
        intencao.putExtra(Constants.owner, adapterRepositorio.repos[position].owner.login)
        intencao.putExtra(Constants.repositorio, adapterRepositorio.repos[position].name)
        startActivity(intencao)
    }


}
