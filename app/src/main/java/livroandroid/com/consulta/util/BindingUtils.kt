package livroandroid.com.consulta.util

import android.widget.TextView
import androidx.databinding.BindingAdapter
import livroandroid.com.consulta.entities.Adress

@BindingAdapter("setZipCode")
fun TextView.setZipCode(item: Adress?) {
    item?.let {
        text = it.zipCode
    }
}

@BindingAdapter("setStreet")
fun TextView.setStreet(item: Adress?) {
    item?.let {
        text = it.street
    }
}

@BindingAdapter("setDistrict")
fun TextView.setDistrict(item: Adress?) {
    item?.let {
        text = it.district
    }
}