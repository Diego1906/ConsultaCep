package livroandroid.com.consultacep.cep.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import livroandroid.com.consultacep.repository.AdressRepository

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