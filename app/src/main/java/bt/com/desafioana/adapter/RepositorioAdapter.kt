package bt.com.desafioana.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import bt.com.desafioana.databinding.ItemRepositorioBinding
import bt.com.desafioana.modelo.Repositorio
import com.bumptech.glide.Glide


class RepositorioAdapter(private val repos: List<Repositorio>,
                         private val listener: RecyclerViewClickListener) : RecyclerView.Adapter<RepositorioAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemRepositorioBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding(repos[position])
        holder.itemRepositoryBinding.cardRepositorio.setOnClickListener {
            listener.onRecyclerViewItemClick(position)
        }

    }


    override fun getItemCount(): Int {
        return repos.size
    }

    inner class ViewHolder(
        val itemRepositoryBinding: ItemRepositorioBinding
    ) : RecyclerView.ViewHolder(itemRepositoryBinding.root) {
        fun binding(repo: Repositorio) {
            itemRepositoryBinding.nome.text = repo.fullName
            itemRepositoryBinding.descricao.text = repo.description
            itemRepositoryBinding.forkQtd.text = repo.forks.toString()
            itemRepositoryBinding.starQtd.text = repo.stars.toString()
            itemRepositoryBinding.username.text = repo.owner.login


            Glide.with(itemRepositoryBinding.iconeUsuario)
                .load(repo.owner.icone_usuario)
                .into(itemRepositoryBinding.iconeUsuario)




        }


    }
    interface RecyclerViewClickListener{
        fun onRecyclerViewItemClick(position: Int)
    }
}




