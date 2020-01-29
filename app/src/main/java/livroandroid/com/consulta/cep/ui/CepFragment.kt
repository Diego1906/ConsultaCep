package livroandroid.com.consulta.cep.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_cep.*
import livroandroid.com.consulta.R
import livroandroid.com.consulta.cep.viewmodel.CepViewModel
import livroandroid.com.consulta.cep.viewmodel.CepViewModelFactory
import livroandroid.com.consulta.databinding.FragmentCepBinding
import livroandroid.com.consulta.network.RetroFitConfig
import livroandroid.com.consulta.repository.AdressRepository

class CepFragment : Fragment() {

    private lateinit var viewModel: CepViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding =
            DataBindingUtil.inflate<FragmentCepBinding>(
                inflater,
                R.layout.fragment_cep,
                container,
                false
            )

        val repository = AdressRepository(RetroFitConfig())

        val viewModelFactory = CepViewModelFactory(repository)

        viewModel = ViewModelProviders
            .of(this, viewModelFactory)
            .get(CepViewModel::class.java)

        binding.cepViewModel = viewModel
        binding.setLifecycleOwner(this)

        createObservable()

        return binding.root
    }

    override fun onStart() {
        super.onStart()

        btn_search.setOnClickListener {

            val cep = txt_cep_search.editableText.toString()
            if (cep.isEmpty()) {
                Snackbar.make(
                    this.requireView(),
                    getString(R.string.preencha_o_cep),
                    Snackbar.LENGTH_LONG
                ).setAnchorView(R.id.card_cep)
                    .show()

                return@setOnClickListener
            }

            viewModel.showSpinner(true)
            viewModel.cleanFields()
            viewModel.searchAdress(cep)
            viewModel.showSpinner()
        }
    }

    private fun createObservable() {
        viewModel.spinner.observe(viewLifecycleOwner, Observer { value ->
            value.let { show ->
                spinner.visibility = if (show) View.VISIBLE else View.INVISIBLE
            }
        })
    }
}
