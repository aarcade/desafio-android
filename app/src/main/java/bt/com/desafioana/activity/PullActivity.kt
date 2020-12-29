package bt.com.desafioana.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import bt.com.desafioana.adapter.PullAdapter
import bt.com.desafioana.databinding.ActivityPullBinding
import kotlinx.android.synthetic.main.activity_pull.*
import bt.com.desafioana.viewmodel.PullViewModel as PullViewModel1

class PullActivity : AppCompatActivity(), PullAdapter.RecyclerClickListener{

    private var owner = ""
    private var repositorio = ""
    private lateinit var bindingPull: ActivityPullBinding
    private lateinit var viewModelPull: PullViewModel1


    private val adapterPull = PullAdapter(ArrayList(), this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingPull = ActivityPullBinding.inflate(layoutInflater)
        bindingPull.toolBarPull
        setContentView(bindingPull.root)
        owner= intent.getStringExtra(RepositoriosActivity.Constants.owner).toString()
        repositorio = intent.getStringExtra(RepositoriosActivity.Constants.repositorio).toString()

        bindingPull.pullRecycler.adapter = adapterPull
        bindingPull.pullRecycler.layoutManager = LinearLayoutManager(this)
        bindingPull.pullRecycler.setHasFixedSize(true)
        setSupportActionBar(toolBarPull)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        bindingPull.toolBarPull.title = repositorio
        viewModelPull = ViewModelProvider(this).get(PullViewModel1::class.java)
        viewModelPull.liveDataPullSucesso.observe(this, Observer {
            adapterPull.listPull.addAll(it)
            adapterPull.notifyDataSetChanged()

        })
        viewModelPull.getPullRequests(owner, repositorio)




    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item?.itemId) {
            android.R.id.home -> finish()
        }
        return false
    }

    override fun onRecyclerClickListener(position: Int) {
        val url = adapterPull.listPull[position].html_url
        val intencaoPull= Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intencaoPull)

    }
}