package livroandroid.com.consulta.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import livroandroid.com.consulta.repository.AdressRepository

class AdressViewModelFactory(
    private val adressRepository: AdressRepository,
    private val application: Application
) :
    ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AdressViewModel::class.java)) {
            return AdressViewModel(adressRepository, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}