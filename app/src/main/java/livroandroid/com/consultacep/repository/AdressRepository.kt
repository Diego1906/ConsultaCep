package livroandroid.com.consultacep.repository

import livroandroid.com.consultacep.cep.entities.Adress
import livroandroid.com.consultacep.network.RetroFitConfig
import retrofit2.Call
import retrofit2.Callback

class AdressRepository(val network: RetroFitConfig) {

    suspend fun searchAdress(cep: String): Adress {
        return network.getService().getRemoteAdress(cep)
    }
}

