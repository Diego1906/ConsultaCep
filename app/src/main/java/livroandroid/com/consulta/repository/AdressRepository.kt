package livroandroid.com.consulta.repository

import livroandroid.com.consulta.entities.Adress
import livroandroid.com.consulta.network.RetroFitConfig

class AdressRepository(private val network: RetroFitConfig) {

    suspend fun searchAdress(cep: String): Adress {
        return network.getService().getRemoteAdress(cep)
    }

    suspend fun searchListAdress(uf: String, cidade: String, rua: String): List<Adress> {
        return network.getService().getRemoteListAdress(uf, cidade, rua)
    }
}

