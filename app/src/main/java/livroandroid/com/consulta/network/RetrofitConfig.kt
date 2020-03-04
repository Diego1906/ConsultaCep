package livroandroid.com.consulta.network

import livroandroid.com.consulta.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetroFitConfig : IService {

    private var retrofit: Retrofit

    init {
        retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.URL_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    override fun getService(): IRetrofitAPI {
        return retrofit.create(IRetrofitAPI::class.java)
    }
}