package bt.com.desafioana.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bt.com.desafioana.Repository.RepositorioRepository
import bt.com.desafioana.modelo.Repositorio
import kotlinx.coroutines.launch

class RepositorioViewModel(private val repositorio: RepositorioRepository): ViewModel(){
    private val repositorioLista: MutableLiveData<List<Repositorio>> = MutableLiveData()
    private val excecao: MutableLiveData<String> = MutableLiveData()
    fun listaRepositorio(pagina: Int){
        viewModelScope.launch {
            val response = repositorio.buscaRepositorio(pagina)
            if (response.isSuccessful){
                repositorioLista.postValue(response.body())
            }else{
                excecao.postValue("Algo deu errado!")
            }

        }
    }

}