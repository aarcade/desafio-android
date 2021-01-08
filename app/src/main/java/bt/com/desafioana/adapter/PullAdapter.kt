package bt.com.desafioana.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import bt.com.desafioana.databinding.ItemPullBinding
import bt.com.desafioana.modelo.PullRequest
import com.bumptech.glide.Glide


class PullAdapter(
    val listPull: MutableList<PullRequest>,
    private val recyclerRepositoryClickListener: RecyclerClickListener
) : RecyclerView.Adapter<PullAdapter.ViewHolder>() {


    override fun getItemCount(): Int {
        return listPull.size
    }

    override fun onBindViewHolder(holder: PullAdapter.ViewHolder, position: Int) {
        holder.bindingPull(listPull[position])
        holder.itemPull.cardPull.setOnClickListener {
            recyclerRepositoryClickListener.onRecyclerClickListener(position)
        }

    }

    inner class ViewHolder(val itemPull: ItemPullBinding) : RecyclerView.ViewHolder(itemPull.root) {

        fun bindingPull(pullRequest: PullRequest) {
            itemPull.tituloPull.text = pullRequest.title
            itemPull.descricaoPull.text = pullRequest.body
            itemPull.usernamePull.text = pullRequest.owner.login
            itemPull.dataPull.text = pullRequest.created_at
            Glide.with(itemPull.iconeUsuarioPull)
                .load(pullRequest.owner.icone_usuario)
                .circleCrop()
                .into(itemPull.iconeUsuarioPull)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            PullAdapter.ViewHolder {
        return ViewHolder(
            ItemPullBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }


    interface RecyclerClickListener {
        fun onRecyclerClickListener(position: Int)
    }


}