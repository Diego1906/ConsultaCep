package livroandroid.com.consulta.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*
import livroandroid.com.consulta.R
import livroandroid.com.consulta.model.AddressObject
import livroandroid.com.consulta.repository.IRepositoryAddress

class AddressViewModel(
    private val repository: IRepositoryAddress, application: Application
) : AndroidViewModel(application) {

    private val TAG = javaClass.simpleName

    private val context by lazy {
        getApplication<Application>().baseContext
    }

    private val viewModelJob = Job()

    private val uiScope by lazy {
        CoroutineScope(Dispatchers.Main + viewModelJob)
    }

    private var _postalCode = MutableLiveData<String>()
    val postalCode: LiveData<String>
        get() = _postalCode

    private var _street = MutableLiveData<String>()
    val street: LiveData<String>
        get() = _street

    private var _neighborhood = MutableLiveData<String>()
    val neighborhood: LiveData<String>
        get() = _neighborhood

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

    private val _listAddress = MutableLiveData<List<AddressObject>>()
    val listAddress: LiveData<List<AddressObject>>
        get() = _listAddress

    private val _address = MutableLiveData<AddressObject>()
    val address: LiveData<AddressObject>
        get() = _address

    private val _connectionIsActive = MutableLiveData<Boolean>()
    val connectionIsActive: LiveData<Boolean>
        get() = _connectionIsActive

    fun onSearchAddress(postalCode: String) {
        uiScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    repository.searchAddress(postalCode).let {
                        if (it.postalCode.isNullOrEmpty()) {
                            _toast.postValue(
                                context.getString(R.string.not_found)
                            )
                        } else {
                            _address.postValue(it)
                        }
                    }
                } catch (ex: Exception) {
                    Log.e(TAG, "${context.getString(R.string.exception)} ${ex.message}")
                    _toast.postValue(
                        "${context.getString(R.string.not_found_by_postal_code)} ${ex.message}"
                    )
                }
            }
            onProgressBarShow(false)
        }
    }

    fun onSearchListAddress(values: Triple<String, String, String>) {
        uiScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    repository.searchListAddress(values).let {
                        if (it.isNotEmpty())
                            _listAddress.postValue(it)
                        else
                            _toast.postValue(context.getString(R.string.not_found))
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
        _postalCode.value = null
        _street.value = null
        _neighborhood.value = null
        _city.value = null
        _state.value = null
        _progressBar.value = null
        _snackBar.value = null
        _toast.value = null
        _connectionIsActive.value = null
    }

    fun onFillFields(address: AddressObject) {
        _postalCode.postValue(address.postalCode)
        _street.postValue(address.street)
        _neighborhood.postValue(address.neighborhood)
        _city.postValue(address.city)
        _state.postValue(address.state)
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

    fun onConnectionIsActive(value: Boolean) {
        _connectionIsActive.value = value
    }
}
