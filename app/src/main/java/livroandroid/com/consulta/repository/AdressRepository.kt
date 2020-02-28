package livroandroid.com.consulta.repository

import livroandroid.com.consulta.entities.Adress
import livroandroid.com.consulta.network.IService

class AdressRepository(private val service: IService) : IRepository {

    override suspend fun searchAdress(cep: String): Adress {
        return service.getService().getRemoteAdress(cep)
    }

    override suspend fun searchListAdress(uf: String, cidade: String, rua: String): List<Adress> {
        return service.getService().getRemoteListAdress(uf, cidade, rua)
    }
}

