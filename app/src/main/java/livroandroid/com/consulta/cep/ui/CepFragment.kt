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
import livroandroid.com.consulta.databinding.FragmentCepBinding
import livroandroid.com.consulta.network.RetroFitConfig
import livroandroid.com.consulta.repository.AdressRepository
import livroandroid.com.consulta.util.Toast
import livroandroid.com.consulta.util.setTitle
import livroandroid.com.consulta.viewmodel.AdressViewModel
import livroandroid.com.consulta.viewmodel.AdressViewModelFactory

class CepFragment : Fragment() {

    private lateinit var adressViewModel: AdressViewModel

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

        val viewModelFactory = AdressViewModelFactory(repository)

        adressViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(AdressViewModel::class.java)

        binding.adressViewModel = adressViewModel
        binding.setLifecycleOwner(this)

        adressViewModel.snackbar.observe(viewLifecycleOwner, Observer { msg ->
            msg?.let {
                Snackbar.make(this.requireView(), it, Snackbar.LENGTH_SHORT).apply {
                    setAnchorView(R.id.card_cep)
                    show()
                }
            }
        })

        adressViewModel.toast.observe(viewLifecycleOwner, Observer {
            it.Toast(application)
        })

        adressViewModel.error.observe(viewLifecycleOwner, Observer {
            it.Toast(application)
        })

        return binding.root
    }

    override fun onStart() {
        super.onStart()

        this.setTitle(getString(R.string.consulta_por_cep))

        btn_search.setOnClickListener {
            adressViewModel.onCleanFields()

            val cep = txt_cep_search.editableText.toString()
            if (cep.isEmpty()) {
                adressViewModel.onSnackbarShown(getString(R.string.preencha_o_cep))
                return@setOnClickListener
            } else if (cep.length < 8) {
                adressViewModel.onSnackbarShown(getString(R.string.digite_cep_completo))
                return@setOnClickListener
            }

            adressViewModel.onProgressBarShown(true)
            adressViewModel.onSearchAdress(cep)
        }
    }
}
