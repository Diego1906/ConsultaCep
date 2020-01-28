package livroandroid.com.consultacep.cep.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_cep.*
import livroandroid.com.consultacep.R
import livroandroid.com.consultacep.cep.viewmodel.CepViewModel
import livroandroid.com.consultacep.cep.viewmodel.CepViewModelFactory
import livroandroid.com.consultacep.network.RetroFitConfig
import livroandroid.com.consultacep.repository.AdressRepository
import java.util.*

class CepFragment : Fragment() {

    private lateinit var viewModel: CepViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_cep, container, false)

        val repository = AdressRepository(RetroFitConfig())

        val viewModelFactory = CepViewModelFactory(repository)

        viewModel = ViewModelProviders
            .of(this, viewModelFactory)
            .get(CepViewModel::class.java)

        createObservable()

        return root
    }

    override fun onStart() {
        super.onStart()

        btn_search.setOnClickListener {

            val cep = txt_cep_search.editableText.toString()
            if (cep.isEmpty()) {
                Toast.makeText(
                    this.activity,
                    "Preencha o campo do Cep.",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            viewModel.showSpinner(true)
            viewModel.cleanFields()
            viewModel.searchAdress(cep)
            viewModel.showSpinner()
        }
    }

    private fun createObservable() {
        viewModel.cep.observe(viewLifecycleOwner, Observer {
            it?.let {
                txt_cep.text = it
            }
        })

        viewModel.rua.observe(viewLifecycleOwner, Observer {
            it?.let {
                txt_rua.text = it
            }
        })

        viewModel.bairro.observe(viewLifecycleOwner, Observer {
            it?.let {
                txt_bairro.text = it
            }
        })

        viewModel.cidade.observe(viewLifecycleOwner, Observer {
            it?.let {
                txt_cidade.text = it
            }
        })

        viewModel.uf.observe(viewLifecycleOwner, Observer {
            it?.let {
                txt_uf.text = it.toUpperCase(Locale.getDefault())
            }
        })

        viewModel.spinner.observe(viewLifecycleOwner, Observer { value ->
            value.let { show ->
                spinner.visibility = if (show) View.VISIBLE else View.GONE
            }
        })
    }
}
