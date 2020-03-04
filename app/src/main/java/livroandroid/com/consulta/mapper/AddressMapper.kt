package livroandroid.com.consulta.mapper

import livroandroid.com.consulta.entities.Address
import livroandroid.com.consulta.model.AddressObject

fun Address.mapToObject() = AddressObject(
    postalCode = this.postalCode,
    street = this.street,
    neighborhood = this.neighborhood,
    city = this.city,
    state = this.state
)