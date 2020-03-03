package livroandroid.com.consulta.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_zip_code.*
import livroandroid.com.consulta.R
import livroandroid.com.consulta.databinding.FragmentZipCodeBinding
import livroandroid.com.consulta.util.onHideKeyboard
import livroandroid.com.consulta.util.onToastShow
import livroandroid.com.consulta.util.setTitle
import livroandroid.com.consulta.viewmodel.AdressViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

const val LENGHT_ZIP_CODE = 8

class ZipCodeFragment : Fragment() {

    private val viewModel: AdressViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<FragmentZipCodeBinding>(
            inflater, R.layout.fragment_zip_code, container, false
        )

        binding.viewModel = viewModel
        binding.setLifecycleOwner(viewLifecycleOwner)

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
            txt_cep_search.editableText.toString().let { cep ->
                if (cep.isEmpty() || cep.length < LENGHT_ZIP_CODE) {
                    if (cep.isEmpty()) {
                        viewModel.onSnackbarShow(getString(R.string.preencha_o_cep))
                    } else {
                        viewModel.onSnackbarShow(getString(R.string.digite_cep_completo))
                    }
                    return@setOnClickListener
                }
                onHideKeyboard()
                viewModel.onCleanFields()
                viewModel.onProgressBarShow(true)
                viewModel.onSearchAdress(cep)
            }
        }
    }

    override fun onStart() {
        super.onStart()

        this.setTitle(getString(R.string.consulta_por_cep))
    }
}
