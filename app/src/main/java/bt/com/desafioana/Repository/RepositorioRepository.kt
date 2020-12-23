package bt.com.desafioana.Repository

import androidx.lifecycle.MutableLiveData
import bt.com.desafioana.modelo.Repositorio
import bt.com.desafioana.webservices.RepositorioService
import retrofit2.Response

class RepositorioRepository (private val repositorio: RepositorioService){
    suspend fun buscaRepositorio(pagina: Int): Response<List<Repositorio>> {
        return repositorio.listaRepositorios(pagina)
    }
}