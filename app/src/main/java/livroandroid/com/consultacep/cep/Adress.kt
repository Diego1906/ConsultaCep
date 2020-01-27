package livroandroid.com.consultacep.cep

data class Adress(
    val cep: String,
    val logradouro: String,
    val bairro: String,
    val localidade: String,
    val uf: String
)