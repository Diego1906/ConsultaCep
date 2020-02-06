package livroandroid.com.consulta.util

import android.widget.TextView
import androidx.databinding.BindingAdapter
import livroandroid.com.consulta.entities.Adress

@BindingAdapter("setCep")
fun TextView.setCep(item: Adress?) {
    item?.let {
       text = it.cep
    }
}

@BindingAdapter("setRua")
fun TextView.setRua(item: Adress?) {
    item?.let {
        text = it.rua
    }
}

@BindingAdapter("setBairro")
fun TextView.setBairro(item: Adress?) {
    item?.let {
        text = it.bairro
    }
}