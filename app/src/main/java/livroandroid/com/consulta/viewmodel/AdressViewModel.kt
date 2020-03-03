package livroandroid.com.consulta.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*
import livroandroid.com.consulta.R
import livroandroid.com.consulta.entities.Adress
import livroandroid.com.consulta.repository.IRepository

class AdressViewModel(
    private val repository: IRepository, application: Application
) : AndroidViewModel(application) {

    private val TAG = javaClass.simpleName

    private val context by lazy {
        getApplication<Application>().baseContext
    }

    private val viewModelJob = Job()

    private val uiScope by lazy {
        CoroutineScope(Dispatchers.Main + viewModelJob)
    }

    private var _zipCode = MutableLiveData<String>()
    val zipCode: LiveData<String>
        get() = _zipCode

    private var _street = MutableLiveData<String>()
    val street: LiveData<String>
        get() = _street

    private var _district = MutableLiveData<String>()
    val district: LiveData<String>
        get() = _district

    private var _city = MutableLiveData<String>()
    val city: LiveData<String>
        get() = _city

    private var _state = MutableLiveData<String>()
    val state: LiveData<String>
        get() = _state

    private val _progressBar = MutableLiveData<Boolean>()
    val progressBar: LiveData<Boolean>
        get() = _progressBar

    private val _snackBar = MutableLiveData<String>()
    val snackbar: LiveData<String>
        get() = _snackBar

    private val _toast = MutableLiveData<String>()
    val toast: LiveData<String>
        get() = _toast

    private val _listAdress = MutableLiveData<List<Adress>>()
    val listAdress: LiveData<List<Adress>>
        get() = _listAdress

    private val _adress = MutableLiveData<Adress>()
    val adress: LiveData<Adress>
        get() = _adress

    fun onSearchAdress(cep: String) {
        uiScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    repository.searchAdress(cep).let {
                        if (it.zipCode.isNullOrEmpty()) {
                            _toast.postValue(
                                context.getString(R.string.nao_foi_possivel_localizar)
                            )
                        } else {
                            _adress.postValue(it)
                        }
                    }
                } catch (ex: Exception) {
                    Log.e(TAG, "${context.getString(R.string.exception)} ${ex.message}")
                    _toast.postValue(
                        "${context.getString(R.string.nao_foi_possivel_consultar_cep)} ${ex.message}"
                    )
                }
            }
            onProgressBarShow(false)
        }
    }

    fun onSearchListAdress(uf: String, cidade: String, rua: String) {
        uiScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    repository.searchListAdress(uf, cidade, rua).let {
                        if (it.isNotEmpty())
                            _listAdress.postValue(it)
                        else
                            _toast.postValue(context.getString(R.string.nao_foi_possivel_localizar))
                    }
                } catch (ex: Exception) {
                    Log.e(TAG, "${context.getString(R.string.exception)} ${ex.message}")
                    _toast.postValue("${context.getString(R.string.erro_inesperado)} ${ex.message}")
                }
            }
            onProgressBarShow(false)
        }
    }

    fun onCleanFields() {
        _zipCode.value = null
        _street.value = null
        _district.value = null
        _city.value = null
        _state.value = null
        _progressBar.value = null
        _snackBar.value = null
        _toast.value = null
    }

    fun onFillFields(adress: Adress) {
        _zipCode.postValue(adress.zipCode)
        _street.postValue(adress.street)
        _district.postValue(adress.district)
        _city.postValue(adress.city)
        _state.postValue(adress.state)
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun onProgressBarShow(value: Boolean) {
        _progressBar.value = value
    }

    fun onSnackbarShow(msg: String) {
        _snackBar.value = msg
    }
}
