package bt.com.desafioana.activity.repositorio

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import bt.com.desafioana.R
import bt.com.desafioana.activity.pull.PullActivity
import bt.com.desafioana.adapter.RepositorioAdapter
import bt.com.desafioana.databinding.ActivityRepositorioBinding
import bt.com.desafioana.modelo.Repositorio


class RepositoriosActivity : AppCompatActivity(), RepositorioAdapter.OnItemClickListener{
    private val exampleList = generateDummyList(500)
    private val adapter = RepositorioAdapter(exampleList, this)
    val page=1
    val isLoading = false
    val limit =10



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityRepositorioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recycler_view = findViewById<RecyclerView>(R.id.repository_recycler)
        recycler_view?.adapter = adapter
        recycler_view?.layoutManager = LinearLayoutManager(this)
        recycler_view?.setHasFixedSize(true)



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


    }

}
