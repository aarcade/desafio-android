package bt.com.desafioana.modelo

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Owner (
    val login: String,
    val icone_usuario: String
) : Parcelable