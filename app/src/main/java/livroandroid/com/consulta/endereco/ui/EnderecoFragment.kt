package livroandroid.com.consulta.endereco.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_endereco.*
import livroandroid.com.consulta.R
import livroandroid.com.consulta.network.RetroFitConfig
import livroandroid.com.consulta.repository.AdressRepository
import livroandroid.com.consulta.util.SnackBarShow
import livroandroid.com.consulta.util.Toast
import livroandroid.com.consulta.util.setTitle
import livroandroid.com.consulta.viewmodel.AdressViewModel
import livroandroid.com.consulta.viewmodel.AdressViewModelFactory

class EnderecoFragment : Fragment() {

    private lateinit var adressViewModel: AdressViewModel
    private lateinit var uf: String
    private lateinit var cidade: String
    private lateinit var rua: String

    private val adapterListEndereco by lazy {
        ListEnderecoAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val application = requireNotNull(activity).application

        val repository = AdressRepository(RetroFitConfig(), application)

        val viewModelFactory = AdressViewModelFactory(repository)

        adressViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(AdressViewModel::class.java)

        adressViewModel.snackbar.observe(viewLifecycleOwner, Observer {
            it.SnackBarShow(this.requireView())
        })

        adressViewModel.toast.observe(viewLifecycleOwner, Observer {
            it.Toast(application)
        })

        adressViewModel.error.observe(viewLifecycleOwner, Observer {
            it.Toast(application)
        })

        adressViewModel.listEndereco.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapterListEndereco.submitList(it)
            }
        })
        return inflater.inflate(R.layout.fragment_endereco, container, false)
    }

    override fun onStart() {
        super.onStart()

        setTitle(getString(R.string.consulta_por_endereco))

        onSetupRecyclerView()

        onInitializeSpinner()

        onItemSelectedListenerSpinner()

        onSetOnClickListener()
    }

    private fun onSetupRecyclerView() {
        recyclerView?.apply {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            adapter = adapterListEndereco
        }
    }

    private fun onInitializeSpinner() {
        spinner_estado?.let {
            it.adapter = ArrayAdapter(
                this.requireContext(),
                android.R.layout.simple_spinner_dropdown_item,
                resources.getStringArray(R.array.Estados)
            )
        }
    }

    private fun onItemSelectedListenerSpinner() {
        spinner_estado.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(
                parent: AdapterView<*>?, view: View?, position: Int, id: Long
            ) {
                uf = spinner_estado.selectedItem.toString()
            }
        }
    }

    private fun onSetOnClickListener() {
        btn_pesquisar_endereco.setOnClickListener {
            if (onValidateFields())
                return@setOnClickListener

            adressViewModel.onSearchListAdress(uf, cidade, rua)
        }
    }

    private fun onValidateFields(): Boolean {
        cidade = edit_text_cidade.editableText.toString()
        rua = edit_text_rua.editableText.toString()

        if (cidade.isEmpty()) {
            adressViewModel.onSnackbarShown("Preencha o campo cidade")
            return true
        } else if (rua.isEmpty()) {
            adressViewModel.onSnackbarShown("Preencha o campo rua")
            return true
        }
        return false
    }
}
