package bt.com.desafioana.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import bt.com.desafioana.R
import bt.com.desafioana.modelo.PullRequest

class PullAdapter(val pullList: List<PullRequest>): RecyclerView.Adapter<PullAdapter.PullAdapterViewHolder>() {



    class PullAdapterViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){
        val iconeUsuario: ImageView = itemView.findViewById(R.id.icone_usuario_pull)
        val username: TextView = itemView.findViewById(R.id.username_pull)
        val titulo: TextView = itemView.findViewById(R.id.titulo_pull)
        val body: TextView = itemView.findViewById(R.id.descricao_pull)
        val criacao:TextView = itemView.findViewById(R.id.data_pull)
        val nomeAutor: TextView = itemView.findViewById(R.id.nome_autor_pull)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PullAdapterViewHolder {
            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_pull, parent, false)
            return PullAdapterViewHolder(itemView)
    }

    override fun getItemCount(): Int {
       return pullList.size
    }

    override fun onBindViewHolder(holder: PullAdapterViewHolder, position: Int) {
        val currentItem = pullList[position]
        holder.let {
            it.titulo.text = currentItem.titulo
            it.body.text = currentItem.body
            it.username.text= currentItem.userName
            it.iconeUsuario.setImageResource(currentItem.icone_usuario)
            it.criacao.text=currentItem.criacao
            it.nomeAutor.text =currentItem.nomeAutor
        }

    }

}