package livroandroid.com.consulta.network

import livroandroid.com.consulta.entities.Address
import retrofit2.http.GET
import retrofit2.http.Path

interface IRetrofitAPI {
    @GET("{postalCode}/json/")
    suspend fun getRemoteAddress(@Path("postalCode") postalCode: String): Address

    @GET("{state}/{city}/{street}/json/")
    suspend fun getRemoteListAddress(
        @Path("state") state: String,
        @Path("city") city: String,
        @Path("street") street: String
    ): List<Address>
}
