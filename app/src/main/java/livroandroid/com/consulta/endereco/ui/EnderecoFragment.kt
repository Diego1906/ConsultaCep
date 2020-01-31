package livroandroid.com.consulta.endereco.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.view.get
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_endereco.*
import livroandroid.com.consulta.R
import livroandroid.com.consulta.cep.viewmodel.CepViewModel
import livroandroid.com.consulta.cep.viewmodel.CepViewModelFactory
import livroandroid.com.consulta.network.RetroFitConfig
import livroandroid.com.consulta.repository.AdressRepository
import livroandroid.com.consulta.util.setTitle

/**
 * A simple [Fragment] subclass.
 */
class EnderecoFragment : Fragment() {

    private lateinit var viewModel: CepViewModel

    private lateinit var uf: String
    private lateinit var cidade: String
    private lateinit var rua: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_endereco, container, false)

        val application = requireNotNull(activity).application

        val repository = AdressRepository(RetroFitConfig(), application)

        val viewModelFactory = CepViewModelFactory(repository)

        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(CepViewModel::class.java)

        viewModel.snackbar.observe(viewLifecycleOwner, Observer { msg ->
            msg?.let {
                Snackbar.make(this.requireView(), msg, Snackbar.LENGTH_SHORT).apply {
                    //setAnchorView(R.id.card_cep)
                    show()
                }
            }
        })

        return root
    }

    override fun onStart() {
        super.onStart()

        this.setTitle(getString(R.string.consulta_por_endereco))

        val estados = resources.getStringArray(R.array.Estados)
        if (spinner_estado != null) {
            val adapter =
                ArrayAdapter(
                    this.requireContext(),
                    android.R.layout.simple_spinner_dropdown_item,
                    estados
                )
            spinner_estado.adapter = adapter
        }

        spinner_estado.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                uf = spinner_estado.selectedItem.toString()
            }
        }

        btn_pesquisar_endereco.setOnClickListener {

            cidade = edit_text_cidade.editableText.toString()
            rua = edit_text_rua.editableText.toString()

//            if (cidade.isEmpty()) {
//                viewModel.onSnackbarShown("Preencha o campo cidade")
//                return@setOnClickListener
//            } else if (rua.isEmpty()) {
//                viewModel.onSnackbarShown("Preencha o campo rua")
//                return@setOnClickListener
//            }

            viewModel.onSearchListAdress(uf, cidade, rua)
        }
    }
}
