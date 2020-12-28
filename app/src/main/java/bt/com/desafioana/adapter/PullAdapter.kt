package bt.com.desafioana.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import bt.com.desafioana.R
import bt.com.desafioana.modelo.PullRequest
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_pull.view.*


class PullAdapter(private val listPull: List<PullRequest>,
                  private val recyclerRepositoryClickListener: RecyclerClickListener
) : RecyclerView.Adapter<PullAdapter.ViewHolder>() {


    override fun getItemCount(): Int {
        return listPull.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.loadProfileImage(listPull[position].owner.icone_usuario)
        holder.data.text = listPull[position].created_at
        holder.descricao_pull.text = listPull[position].body
        holder.titulo_pull.text = listPull[position].title
        holder.username.text = listPull[position].owner.login
        holder.itemView.setOnClickListener{
            recyclerRepositoryClickListener.onRecyclerClickListener(position)
        }


    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titulo_pull = itemView.titulo_pull
        val descricao_pull = itemView.descricao_pull
        val username= itemView.username_pull
        val data = itemView.data_pull
        val iconeUsuario = itemView.icone_usuario_pull

        fun loadProfileImage(url: String) {
            if (url.isBlank()) {
                Picasso.get()
                    .load(R.drawable.ic_usuario)
                    .placeholder(R.drawable.ic_usuario)
                    .error(R.drawable.ic_usuario)
                    .fit()
                    .into(iconeUsuario)
            } else {
                Picasso.get()
                    .load(url)
                    .placeholder(R.drawable.ic_usuario)
                    .error(R.drawable.ic_usuario)
                    .fit()
                    .into(iconeUsuario)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_pull, parent, false))
    }


    interface RecyclerClickListener{
        fun onRecyclerClickListener(position: Int)
    }


}