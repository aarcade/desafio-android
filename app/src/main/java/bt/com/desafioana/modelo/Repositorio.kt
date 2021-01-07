package bt.com.desafioana.modelo

import com.google.gson.annotations.SerializedName


data class Repositorio(
    @SerializedName("total_count") val totalPaginas: Int,
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("full_name") val fullName: String,
    @SerializedName("owner") val owner: Owner,
    @SerializedName("description") val description: String,
    @SerializedName("stargazers_count") val stars: Int,
    @SerializedName("forks_count") val forks: Int
)