package livroandroid.com.consulta.repository

import livroandroid.com.consulta.model.AddressObject

interface IRepositoryAddress {
    suspend fun searchAddress(postalCode: String): AddressObject
    suspend fun searchListAddress(values: Triple<String, String, String>): List<AddressObject>
}