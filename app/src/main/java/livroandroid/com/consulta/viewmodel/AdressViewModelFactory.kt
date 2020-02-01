package livroandroid.com.consulta.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import livroandroid.com.consulta.repository.AdressRepository

class AdressViewModelFactory(private val adressRepository: AdressRepository) :
    ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AdressViewModel::class.java)) {
            return AdressViewModel(adressRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}