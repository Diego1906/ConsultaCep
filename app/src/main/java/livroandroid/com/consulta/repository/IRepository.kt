package livroandroid.com.consulta.repository

import livroandroid.com.consulta.entities.Adress

interface IRepository {
    suspend fun searchAdress(cep: String): Adress

    suspend fun searchListAdress(uf: String, cidade: String, rua: String): List<Adress>
}