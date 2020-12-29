package bt.com.desafioana.adapter

import android.os.Handler
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import bt.com.desafioana.databinding.ItemRepositorioBinding
import bt.com.desafioana.modelo.Repositorio
import bt.com.desafioana.utils.Constants
import com.bumptech.glide.Glide


class RepositorioAdapter(val repos: MutableList<Repositorio>,
                         private val listener: RecyclerViewClickListener) : RecyclerView.Adapter<RepositorioAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            ViewHolder {
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

    fun addData(repos: MutableList<Repositorio>) {
        this.repos.addAll(repos)
        notifyDataSetChanged()
    }


    fun addLoadingView() {
        val nulo = null
        //Add loading item
        Handler().post {
            nulo?.let { repos.add(it) }
            notifyItemInserted(repos.size - 1)
        }
    }

    fun removeLoadingView() {
        //Remove loading item
        if (repos.size != 0) {
            repos.removeAt(repos.size - 1)
            notifyItemRemoved(repos.size)
        }
    }

    interface RecyclerViewClickListener{
        fun onRecyclerViewItemClick(position: Int)
    }

    override fun getItemViewType(position: Int): Int {
        return if (repos[position] == null) {
            Constants.VIEW_TYPE_LOADING
        } else {
            Constants.VIEW_TYPE_ITEM
        }
    }
}




