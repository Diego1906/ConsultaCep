package livroandroid.com.consultacep.cep

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CepViewModel : ViewModel() {

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

    private fun cleanFiels() {
        _cep.value = null
        _rua.value = null
        _bairro.value = null
        _cidade.value = null
        _uf.value = null
    }

    private fun fillFields(adress: Adress) {
        _cep.value = adress.cep
        _rua.value = adress.logradouro
        _bairro.value = adress.bairro
        _cidade.value = adress.localidade
        _uf.value = adress.uf
    }

    fun searchAdress(cep: String) {

    }
}