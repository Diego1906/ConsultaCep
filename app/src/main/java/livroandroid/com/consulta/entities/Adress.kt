package livroandroid.com.consulta.entities

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Adress(
    @SerializedName("cep")
    val zipCode: String? = null,

    @SerializedName("logradouro")
    val street: String? = null,

    @SerializedName("bairro")
    val district: String? = null,

    @SerializedName("localidade")
    val city: String? = null,

    @SerializedName("uf")
    val state: String? = null
) : Serializable