package bt.com.desafioana.activity.pull

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import bt.com.desafioana.R
import bt.com.desafioana.adapter.PullAdapter
import bt.com.desafioana.databinding.ActivityPullBinding
import bt.com.desafioana.modelo.PullRequest

class PullActivity: AppCompatActivity() {

    private val exampleList = generateDummyList(500)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bindingPull = ActivityPullBinding.inflate(layoutInflater)
        setContentView(bindingPull.root)
        setSupportActionBar(bindingPull.toolBarPull)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        val pull_recycler = findViewById<RecyclerView>(R.id.pull_recycler)
        pull_recycler?.adapter = PullAdapter(exampleList)
        pull_recycler?.layoutManager = LinearLayoutManager(this)
        pull_recycler?.setHasFixedSize(true)


    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId){
            android.R.id.home->finish()
        }
        return false
}

    private fun generateDummyList(size: Int): List<PullRequest> {
        val list = ArrayList<PullRequest>()
        for (i in 0 until size) {
            val drawable = when (i % 3) {
                0 -> R.drawable.ic_usuario
                else -> R.drawable.ic_usuario
            }
            val item = PullRequest(drawable, "Java","12/12/20", "lu", "Luana","xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx")
            list += item
        }
        return list
    }

}