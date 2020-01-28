package livroandroid.com.consultacep.network

import livroandroid.com.consultacep.cep.entities.Adress
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitAPI {

    @GET("{cep}/json/")
    suspend fun getRemoteAdress(@Path("cep") cep: String): Adress
}