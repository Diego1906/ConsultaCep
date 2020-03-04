package livroandroid.com.consulta.util

import android.widget.TextView
import androidx.databinding.BindingAdapter
import livroandroid.com.consulta.model.AddressObject

@BindingAdapter("setPostalCode")
fun TextView.setPostalCode(item: AddressObject?) {
    item?.let {
        text = it.postalCode
    }
}

@BindingAdapter("setStreet")
fun TextView.setStreet(item: AddressObject?) {
    item?.let {
        text = it.street
    }
}

@BindingAdapter("setNeighborhood")
fun TextView.setNeighborhood(item: AddressObject?) {
    item?.let {
        text = it.neighborhood
    }
}