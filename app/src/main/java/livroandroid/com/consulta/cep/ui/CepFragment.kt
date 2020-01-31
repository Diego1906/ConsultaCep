package livroandroid.com.consulta.cep.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
import livroandroid.com.consulta.util.setTitle


class CepFragment : Fragment() {

    private lateinit var viewModel: CepViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentCepBinding>(
            inflater,
            R.layout.fragment_cep,
            container,
            false
        )

        val application = requireNotNull(activity).application

        val repository = AdressRepository(RetroFitConfig(), application)

        val viewModelFactory = CepViewModelFactory(repository)

        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(CepViewModel::class.java)

        binding.cepViewModel = viewModel
        binding.setLifecycleOwner(this)

        viewModel.snackbar.observe(viewLifecycleOwner, Observer { msg ->
            msg?.let {
                Snackbar.make(this.requireView(), msg, Snackbar.LENGTH_SHORT).apply {
                    setAnchorView(R.id.card_cep_form)
                    show()
                }
            }
        })

        viewModel.error.observe(viewLifecycleOwner, Observer { msg ->
            Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
        })

        return binding.root
    }

    override fun onStart() {
        super.onStart()

        this.setTitle(getString(R.string.consulta_por_cep))

        btn_search.setOnClickListener {
            viewModel.onCleanFields()

            val cep = txt_cep_search.editableText.toString()
            if (cep.isEmpty()) {
                viewModel.onSnackbarShown(getString(R.string.preencha_o_cep))
                return@setOnClickListener
            } else if (cep.length < 8) {
                viewModel.onSnackbarShown(getString(R.string.digite_cep_completo))
                return@setOnClickListener
            }

            viewModel.onSpinnerShown(true)
            viewModel.onSearchAdress(cep)
        }
    }
}
