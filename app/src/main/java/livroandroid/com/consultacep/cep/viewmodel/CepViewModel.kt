package livroandroid.com.consultacep.cep.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import livroandroid.com.consultacep.cep.entities.Adress
import livroandroid.com.consultacep.repository.AdressRepository

class CepViewModel(private val adressRepository: AdressRepository) : ViewModel() {

    val viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private var _cep = MutableLiveData<String>()
    val cep: LiveData<String>
        get() = _cep

    private var _rua = MutableLiveData<String>()
    val rua: LiveData<String>
        get() = _rua

    private var _bairro = MutableLiveData<String>()
    val bairro: LiveData<String>
        get() = _bairro

    private var _cidade = MutableLiveData<String>()
    val cidade: LiveData<String>
        get() = _cidade

    private var _uf = MutableLiveData<String>()
    val uf: LiveData<String>
        get() = _uf

    private val _spinner = MutableLiveData<Boolean>(false)
    val spinner: LiveData<Boolean>
        get() = _spinner

    fun cleanFields() {
        _cep.value = null
        _rua.value = null
        _bairro.value = null
        _cidade.value = null
        _uf.value = null
    }

    fun searchAdress(cep: String) {
        uiScope.launch {
            withContext(Dispatchers.IO) {
                val adress = adressRepository.searchAdress(cep)

                fillFields(adress)
            }
        }
    }

    private fun fillFields(adress: Adress) {
        _cep.postValue(adress.cep)
        _rua.postValue(adress.rua)
        _bairro.postValue(adress.bairro)
        _cidade.postValue(adress.cidade)
        _uf.postValue(adress.uf)
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun showSpinner(value: Boolean = false) {
        _spinner.value = value
    }
}