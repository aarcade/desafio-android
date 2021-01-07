package bt.com.desafioana.modelo

import com.google.gson.annotations.SerializedName


data class PullRequest(

    @SerializedName("html_url") val html_url: String,
    @SerializedName("title") val title: String,
    @SerializedName("created_at") val created_at: String,
    @SerializedName("body") val body: String,
    @SerializedName("user") val owner: Owner
)
