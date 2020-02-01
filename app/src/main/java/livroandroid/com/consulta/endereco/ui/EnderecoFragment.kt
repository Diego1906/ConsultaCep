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
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_endereco.*
import livroandroid.com.consulta.R
import livroandroid.com.consulta.viewmodel.AdressViewModel
import livroandroid.com.consulta.viewmodel.AdressViewModelFactory
import livroandroid.com.consulta.network.RetroFitConfig
import livroandroid.com.consulta.repository.AdressRepository
import livroandroid.com.consulta.util.setTitle

/**
 * A simple [Fragment] subclass.
 */
class EnderecoFragment : Fragment() {

    private lateinit var adressViewModel: AdressViewModel
    private lateinit var uf: String
    private lateinit var cidade: String
    private lateinit var rua: String

    private val adapter by lazy {
        ListEnderecoAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //val root = inflater.inflate(R.layout.fragment_endereco, container, false)

        val application = requireNotNull(activity).application

        val repository = AdressRepository(RetroFitConfig(), application)

        val viewModelFactory = AdressViewModelFactory(repository)

        adressViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(AdressViewModel::class.java)

        adressViewModel.snackbar.observe(viewLifecycleOwner, Observer { msg ->
            msg?.let {
                Snackbar.make(this.requireView(), msg, Snackbar.LENGTH_SHORT).apply {
                    show()
                }
            }
        })

        adressViewModel.listEndereco.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.listAdress = it
            }
        })

        // return root
        return inflater.inflate(R.layout.fragment_endereco, container, false)
    }

    override fun onStart() {
        super.onStart()

        recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)

        recyclerView?.adapter = adapter

        this.setTitle(getString(R.string.consulta_por_endereco))

        onInitializeSpinner()

        onItemSelectedListenerSpinner()

        btn_pesquisar_endereco.setOnClickListener {
            if (onValidateParameters())
                return@setOnClickListener

            adressViewModel.onSearchListAdress(uf, cidade, rua)
        }
    }

    private fun onValidateParameters(): Boolean {
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

    private fun onInitializeSpinner() {
        spinner_estado?.let {
            it.adapter = ArrayAdapter(
                this.requireContext(),
                android.R.layout.simple_spinner_dropdown_item,
                resources.getStringArray(R.array.Estados)
            )
        }
    }
}
