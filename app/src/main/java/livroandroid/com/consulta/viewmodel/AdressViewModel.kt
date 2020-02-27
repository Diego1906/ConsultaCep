package livroandroid.com.consulta.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*
import livroandroid.com.consulta.R
import livroandroid.com.consulta.entities.Adress
import livroandroid.com.consulta.repository.AdressRepository

class AdressViewModel(
    private val repository: AdressRepository,
    application: Application
) :
    AndroidViewModel(application) {

    private val TAG = javaClass.simpleName

    private val context by lazy {
        getApplication<Application>()
    }

    private val viewModelJob = Job()

    private val uiScope by lazy {
        CoroutineScope(Dispatchers.Main + viewModelJob)
    }

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

    private val _progressBar = MutableLiveData<Boolean>()
    val progressBar: LiveData<Boolean>
        get() = _progressBar

    private val _snackBar = MutableLiveData<String>()
    val snackbar: LiveData<String>
        get() = _snackBar

    private val _toast = MutableLiveData<String>()
    val toast: LiveData<String>
        get() = _toast

    private val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error

    private val _listEndereco = MutableLiveData<List<Adress>>()
    val listEndereco: LiveData<List<Adress>>
        get() = _listEndereco

    fun onSearchAdress(cep: String) {
        uiScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    repository.searchAdress(cep).let {
                        if (it.cep.isNullOrEmpty()) {
                            _toast.postValue(
                                context.getString(R.string.nao_foi_possivel_localizar)
                            )
                        } else {
                            onFillFieldsBackgroundThread(it)
                        }
                    }
                } catch (ex: Exception) {
                    Log.e(TAG, "${context.getString(R.string.exception)} ${ex.message}")
                    _error.postValue(
                        "${context.getString(R.string.nao_foi_possivel_consultar_cep)} ${ex.message}"
                    )
                }
            }
            onProgressBarShown(false)
        }
    }

    fun onSearchListAdress(uf: String, cidade: String, rua: String) {
        uiScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    repository.searchListAdress(uf, cidade, rua).let {
                        if (it.isNotEmpty())
                            _listEndereco.postValue(it)
                        else
                            _toast.postValue(context.getString(R.string.nao_foi_possivel_localizar))
                    }
                } catch (ex: Exception) {
                    Log.e(TAG, "${context.getString(R.string.exception)} ${ex.message}")
                    _error.postValue("${context.getString(R.string.erro_inesperado)} ${ex.message}")
                }
            }
        }
    }

    fun onCleanFields() {
        _cep.value = null
        _rua.value = null
        _bairro.value = null
        _cidade.value = null
        _uf.value = null
    }

    private fun onFillFieldsBackgroundThread(adress: Adress) {
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

    fun onProgressBarShown(value: Boolean) {
        _progressBar.value = value
    }

    fun onSnackbarShown(msg: String) {
        _snackBar.value = msg
    }
}
