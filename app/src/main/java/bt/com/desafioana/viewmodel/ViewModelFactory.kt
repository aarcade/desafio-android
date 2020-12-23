package bt.com.desafioana.viewmodel

import android.app.backup.BackupAgentHelper
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import bt.com.desafioana.Repository.PullRepository
import bt.com.desafioana.Repository.RepositorioRepository
import bt.com.desafioana.webservices.InicializadorRetrofit
import bt.com.desafioana.webservices.PullRequestService
import bt.com.desafioana.webservices.RepositorioService

class ViewModelFactory(private val repositorio: RepositorioService): ViewModelProvider.Factory{


    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when (modelClass) {
        RepositorioViewModel::class.java->{RepositorioViewModel(repositorio)}
        else ->{
            PullViewModel(pullRepository) as T
        }
    }
}