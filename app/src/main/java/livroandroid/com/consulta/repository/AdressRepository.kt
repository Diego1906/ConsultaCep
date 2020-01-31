package livroandroid.com.consulta.repository

import android.app.Application
import android.util.Log
import livroandroid.com.consulta.R
import livroandroid.com.consulta.cep.entities.Adress
import livroandroid.com.consulta.network.RetroFitConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AdressRepository(val network: RetroFitConfig, val application: Application) {

    private val TAG = javaClass.simpleName

    private var listAdress: List<Adress> = listOf()

    suspend fun searchAdress(cep: String): Adress {
        return network.getService().getRemoteAdress(cep)
    }

    fun searchListAdress(uf: String, cidade: String, rua: String): List<Adress> {
        val call = network.getService().getRemoteListAdress(uf, cidade, rua)
        call.enqueue(object : Callback<List<Adress>> {

            override fun onFailure(call: Call<List<Adress>>, t: Throwable) {
                Log.e(TAG, "Error: ${t.message}")
            }

            override fun onResponse(
                call: Call<List<Adress>>,
                response: Response<List<Adress>>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        listAdress = it
                    }
                } else if (response.code() == 500) {
                    Log.e(TAG, application.getString(R.string.error_500))
                } else {
                    Log.e(TAG, application.getString(R.string.generic_error_remote))
                }
            }
        })
        return listAdress
    }
}

