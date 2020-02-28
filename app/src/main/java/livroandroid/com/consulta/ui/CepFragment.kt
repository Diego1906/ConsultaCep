package livroandroid.com.consulta.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_cep.*
import livroandroid.com.consulta.R
import livroandroid.com.consulta.databinding.FragmentCepBinding
import livroandroid.com.consulta.util.setTitle
import livroandroid.com.consulta.util.toastShow
import livroandroid.com.consulta.viewmodel.AdressViewModel
import org.koin.android.ext.android.inject

class CepFragment : Fragment() {

    private val viewModel: AdressViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentCepBinding>(
            inflater,
            R.layout.fragment_cep,
            container,
            false
        ).apply {
            adressViewModel = viewModel
            setLifecycleOwner(viewLifecycleOwner)
        }

        val application = requireNotNull(activity).application

        viewModel.snackbar.observe(viewLifecycleOwner, Observer { msg ->
            msg?.let {
                Snackbar.make(this.requireView(), it, Snackbar.LENGTH_SHORT).apply {
                    setAnchorView(R.id.card_cep)
                    show()
                }
            }
        })

        viewModel.toast.observe(viewLifecycleOwner, Observer {
            it.toastShow(application)
        })

        viewModel.error.observe(viewLifecycleOwner, Observer {
            it.toastShow(application)
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

            viewModel.onProgressBarShown(true)
            viewModel.onSearchAdress(cep)
        }
    }
}
