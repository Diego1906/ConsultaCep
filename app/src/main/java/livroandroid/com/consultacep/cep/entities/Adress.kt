package livroandroid.com.consultacep.cep.entities

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Adress(

    @SerializedName("cep")
    val cep: String? = null,

    @SerializedName("logradouro")
    val rua: String? = null,

    @SerializedName("bairro")
    val bairro: String? = null,

    @SerializedName("localidade")
    val cidade: String? = null,

    @SerializedName("uf")
    val uf: String? = null
) : Serializable