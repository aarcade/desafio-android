package bt.com.desafioana.modelo

import com.google.gson.annotations.SerializedName

data class Repos(

    @SerializedName("items") val items: List<Repositorio>
)