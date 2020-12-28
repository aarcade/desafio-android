package bt.com.desafioana.activity

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import bt.com.desafioana.adapter.PullAdapter
import bt.com.desafioana.databinding.ActivityPullBinding
import bt.com.desafioana.modelo.PullRequest
import bt.com.desafioana.webservices.InicializadorRetrofit.initPull
import kotlinx.android.synthetic.main.activity_pull.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PullActivity : AppCompatActivity() {

    private var owner = ""
    private var repositorio = ""
    private val listPull = ArrayList<PullRequest>()
    private lateinit var bindingPull: ActivityPullBinding

    val pull by lazy { initPull() }

    private val adapter = PullAdapter(listPull)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingPull = ActivityPullBinding.inflate(layoutInflater)
        bindingPull.toolBarPull
        setContentView(bindingPull.root)
        owner= intent.getStringExtra(RepositoriosActivity.Constants.owner).toString()
        repositorio = intent.getStringExtra(RepositoriosActivity.Constants.repositorio).toString()
        bindingPull.pullRecycler.adapter = adapter
        bindingPull.pullRecycler.layoutManager = LinearLayoutManager(this)
        bindingPull.pullRecycler.setHasFixedSize(true)
        setSupportActionBar(toolBarPull)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        bindingPull.toolBarPull.title = repositorio
        getPullRequests(owner,repositorio)



    }
    fun getPullRequests(owner: String, repositorio: String) {
        val apiService = initPull()

        val call = apiService.getPullRequests(owner, repositorio)
        call.enqueue(object : Callback<List<PullRequest>> {
            override fun onFailure(call: Call<List<PullRequest>>, t: Throwable) {
                Log.d("Erro de chamada", t.message.toString())
                Toast.makeText(this@PullActivity, t.message, Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<List<PullRequest>>, response: Response<List<PullRequest>>) {
                if (response.isSuccessful){
                    response.body()?.let {
                        bindingPull.pullRecycler.adapter = PullAdapter(it)

                    }
                }
            }

        })
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item?.itemId) {
            android.R.id.home -> finish()
        }
        return false
    }
}