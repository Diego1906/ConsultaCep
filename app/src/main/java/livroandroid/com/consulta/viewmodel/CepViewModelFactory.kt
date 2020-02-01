package livroandroid.com.consulta.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import livroandroid.com.consulta.repository.AdressRepository

class CepViewModelFactory(private val adressRepository: AdressRepository) :
    ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CepViewModel::class.java)) {
            return CepViewModel(adressRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}