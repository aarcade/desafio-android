package bt.com.desafioana.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import bt.com.desafioana.RecyclerViewLoadMoreScroll
import bt.com.desafioana.adapter.RepositorioAdapter
import bt.com.desafioana.databinding.ActivityRepositorioBinding
import bt.com.desafioana.modelo.Repositorio
import bt.com.desafioana.utils.Constants
import bt.com.desafioana.viewmodel.RepositorioViewModel

class RepositoriosActivity : AppCompatActivity(), RepositorioAdapter.RecyclerViewClickListener {

     var loadMoreItemsRepositorio =  ArrayList<Repositorio>()
    lateinit var linearLayoutManager: RecyclerView.LayoutManager
    lateinit var scrollListener:    RecyclerViewLoadMoreScroll
    private val adapterRepositorio = RepositorioAdapter(loadMoreItemsRepositorio, this)
    private lateinit var binding: ActivityRepositorioBinding
    private lateinit var viewModel: RepositorioViewModel
    private var pagina = 1
    private var lastVisibleItemPosition = Int
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
        setRVLayoutManager()
        setRVScrollListener()


    }


    private fun getRepositorio(pagina: Int) {
        //Add the Loading View
        adapterRepositorio.addLoadingView()
        val start = adapterRepositorio.itemCount
        val end = start + 34
        Handler().postDelayed({
            for (i in start..end) {

                viewModel = ViewModelProvider(this).get(RepositorioViewModel::class.java)
                viewModel.liveDataRepositorioSucesso.observe(this, Observer {
                    adapterRepositorio.repos.addAll(it)
                    adapterRepositorio.notifyDataSetChanged()
                })
                viewModel.getRepositorio(pagina)
            }
            //Remove the Loading View
            adapterRepositorio.removeLoadingView()
            //We adding the data to our main ArrayList
            adapterRepositorio.addData(loadMoreItemsRepositorio)
            //Change the boolean isLoading to false
            scrollListener.setLoaded()
            //Update the recyclerView in the main thread
            binding.repositoryRecycler.post {
                adapterRepositorio.notifyDataSetChanged()
            }
        }, 3000)

    }

    override fun onRecyclerViewItemClick(position: Int) {
        val intencao = Intent(this@RepositoriosActivity, PullActivity::class.java)
        intencao.putExtra(Constants.owner, adapterRepositorio.repos[position].owner.login)
        intencao.putExtra(Constants.repositorio, adapterRepositorio.repos[position].name)
        startActivity(intencao)
    }

    private fun setRVLayoutManager() {
        linearLayoutManager = LinearLayoutManager(this)
        binding.repositoryRecycler.layoutManager = linearLayoutManager
        binding.repositoryRecycler.setHasFixedSize(true)
    }
    private  fun setRVScrollListener() {
        linearLayoutManager = LinearLayoutManager(this)
        scrollListener = RecyclerViewLoadMoreScroll(linearLayoutManager as LinearLayoutManager)
        scrollListener.setOnLoadMoreListener(object :
            RecyclerViewLoadMoreScroll.OnLoadMoreListener {
            override fun onLoadMore() {
                getRepositorio(pagina)
            }

        })
      binding.repositoryRecycler.addOnScrollListener(scrollListener)}





}

























