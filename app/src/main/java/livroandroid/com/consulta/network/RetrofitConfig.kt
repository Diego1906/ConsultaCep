package livroandroid.com.consulta.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetroFitConfig : IService {

    private var retrofit: Retrofit
    private val URL_BASE = "https://viacep.com.br/ws/"

    init {
        retrofit = Retrofit.Builder()
            .baseUrl(URL_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    override fun getService(): IRetrofitAPI {
        return retrofit.create(IRetrofitAPI::class.java)
    }
}