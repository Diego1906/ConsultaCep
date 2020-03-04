package livroandroid.com.consulta.repository

import livroandroid.com.consulta.mapper.mapToObject
import livroandroid.com.consulta.model.AddressObject
import livroandroid.com.consulta.network.IService

class AddressRepository(private val service: IService) : IRepositoryAddress {

    override suspend fun searchAddress(postalCode: String): AddressObject {
        return service.getService().getRemoteAddress(postalCode).mapToObject()
    }

    override suspend fun searchListAddress(
        values: Triple<String, String, String>
    ): List<AddressObject> {
        return service.getService()
            .getRemoteListAddress(state = values.first, city = values.second, street = values.third)
            .map {
                it.mapToObject()
            }
    }
}

