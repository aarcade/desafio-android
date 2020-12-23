package bt.com.desafioana.modelo

data class Repositorio(
        val iconeUsuario: Int,
        //val dono: Owner,
        val userName: String,
        val nomeRepositorio: String,
        val descricaoRepositorio: String,
        val nomeAutor: String,
        val stars: Int,
        val forks: Int)
