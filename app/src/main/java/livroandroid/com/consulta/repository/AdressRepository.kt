package livroandroid.com.consulta.repository

import android.app.Application
import livroandroid.com.consulta.cep.entities.Adress
import livroandroid.com.consulta.network.RetroFitConfig

class AdressRepository(val network: RetroFitConfig, val application: Application) {

    suspend fun searchAdress(cep: String): Adress {
        return network.getService().getRemoteAdress(cep)
    }

//    suspend fun searchAdress(cep: String): Adress {////
////        var adress = Adress()
////
////        try {////
////            Log.e(TAG, "Error: searchAdress")////
////            network.getService().getRemoteAdress(cep).enqueue(object : Callback<Adress> {////
////                override fun onFailure(call: Call<Adress>, t: Throwable) {
////                    Log.e(TAG, "Error: ${t.message}")
////                    Log.e(TAG, "Error: ${call.request().body()}")
////                }
////
////                override fun onResponse(call: Call<Adress>, response: Response<Adress>) {////
////                    when (response.code()) {
////                        200 -> {
////                            adress = response.body()?.copy()!!
////                            Log.e(TAG, "Error: ${response.code()}")
////                        }
////                        else -> {
////                            Log.e(TAG, "Error: ${response.errorBody()}")
////                        }
////                    }
////                }
////            })
////        } catch (ex: Exception) {
////            Log.e(TAG, "Exception: ${ex.message} - ${ex.stackTrace} ")
////        }////
////        return adress
////    }
}

