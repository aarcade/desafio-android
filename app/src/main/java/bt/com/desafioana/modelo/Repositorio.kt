package bt.com.desafioana.modelo

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Repositorio(
        val nomeRepositorio: String,
        val nomeAutor: String,
        val descricaoRepositorio: String,
        val dono: Owner,
        val stars: Int,
        val forks: Int) : Parcelable
