package livroandroid.com.consulta.network

import livroandroid.com.consulta.entities.Adress
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitAPI {
    @GET("{cep}/json/")
    suspend fun getRemoteAdress(@Path("cep") cep: String): Adress

    @GET("{uf}/{cidade}/{rua}/json/")
    fun getRemoteListAdress(
        @Path("uf") uf: String,
        @Path("cidade") cidade: String,
        @Path("rua") rua: String
    ): Call<List<Adress>>
}
