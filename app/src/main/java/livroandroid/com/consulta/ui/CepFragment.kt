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
import livroandroid.com.consulta.util.onHideKeyboard
import livroandroid.com.consulta.util.onToastShow
import livroandroid.com.consulta.util.setTitle
import livroandroid.com.consulta.viewmodel.AdressViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

const val LENGHT_CEP = 8

class CepFragment : Fragment() {

    private val viewModel: AdressViewModel by viewModel()

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

        viewModel.snackbar.observe(viewLifecycleOwner, Observer { msg ->
            msg?.let {
                Snackbar.make(requireView(), it, Snackbar.LENGTH_SHORT).apply {
                    setAnchorView(R.id.card_cep)
                    show()
                }
            }
        })

        viewModel.toast.observe(viewLifecycleOwner, Observer { msg ->
            msg?.let {
                view?.context?.let { context ->
                    it.onToastShow(context)
                }
            }
        })

        viewModel.adress.observe(viewLifecycleOwner, Observer {
            it?.let {
                viewModel.onFillFields(it)
            }
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_search.setOnClickListener {
            val cep = txt_cep_search.editableText.toString()
            if (cep.isEmpty()) {
                viewModel.onSnackbarShown(getString(R.string.preencha_o_cep))
                return@setOnClickListener
            } else if (cep.length < LENGHT_CEP) {
                viewModel.onSnackbarShown(getString(R.string.digite_cep_completo))
                return@setOnClickListener
            }

            onHideKeyboard()
            viewModel.onCleanFields()
            viewModel.onProgressBarShown(true)
            viewModel.onSearchAdress(cep)
        }
    }

    override fun onStart() {
        super.onStart()

        this.setTitle(getString(R.string.consulta_por_cep))
    }
}
