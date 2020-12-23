package bt.com.desafioana.modelo

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class PullRequest (
        val titulo: String,
        @SerializedName("user")
        val dono: Owner,
        val criacao: String,
        val body: String,
        val html_url: String) : Parcelable

