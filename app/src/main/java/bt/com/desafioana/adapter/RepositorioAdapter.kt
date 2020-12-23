package bt.com.desafioana.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import bt.com.desafioana.R
import bt.com.desafioana.modelo.Repositorio

class RepositorioAdapter(
    private val repositorioList: List<Repositorio>,
    private val listener: OnItemClickListener): RecyclerView.Adapter<RepositorioAdapter.RepositorioAdapterViewHolder>(){

   inner class RepositorioAdapterViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val iconeUsuario: ImageView = itemView.findViewById(R.id.icone_usuario)
        val username: TextView = itemView.findViewById(R.id.username)
        val nomeAutor: TextView = itemView.findViewById(R.id.nome_autor)
        val nomeRepositorio: TextView = itemView.findViewById(R.id.nome_repositorio)
        val descricao: TextView = itemView.findViewById(R.id.descricao)
        val start: TextView =itemView.findViewById(R.id.star_qtd)
        val fork: TextView =itemView.findViewById(R.id.fork_qtd)


        init {
            itemView.setOnClickListener(this)
        }

       override fun onClick(v: View?) {
           val position: Int = adapterPosition
           if (position!= RecyclerView.NO_POSITION){
               listener.onItemClick(position)
           }

       }
   }



    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RepositorioAdapterViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_repositorio, parent, false)
        return RepositorioAdapterViewHolder(itemView)

    }
    override fun onBindViewHolder(holder: RepositorioAdapterViewHolder, position: Int) {
        val currentItem = repositorioList[position]
        holder.let {
            it.nomeAutor.text = currentItem.nomeAutor
            it.nomeRepositorio.text = currentItem.nomeRepositorio
            it.descricao.text = currentItem.descricaoRepositorio
            it.start.text = currentItem.stars.toString()
            it.fork.text = currentItem.forks.toString()
            it.username.text= currentItem.dono.login
            it.iconeUsuario.setImageResource(currentItem.dono.icone_usuario)
        }
    }

    override fun getItemCount()= repositorioList.size
    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }

}