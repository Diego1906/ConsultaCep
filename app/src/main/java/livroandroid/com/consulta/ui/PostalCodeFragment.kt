package livroandroid.com.consulta.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_postal_code.*
import livroandroid.com.consulta.R
import livroandroid.com.consulta.databinding.FragmentPostalCodeBinding
import livroandroid.com.consulta.util.Connection
import livroandroid.com.consulta.util.onHideKeyboard
import livroandroid.com.consulta.util.onToastShow
import livroandroid.com.consulta.util.setTitle
import livroandroid.com.consulta.viewmodel.AddressViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

const val LENGHT_POSTAL_CODE = 8

class PostalCodeFragment : Fragment() {

    private val viewModel: AddressViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<FragmentPostalCodeBinding>(
            inflater, R.layout.fragment_postal_code, container, false
        )

        binding.viewModel = viewModel
        binding.setLifecycleOwner(viewLifecycleOwner)

        viewModel.snackbar.observe(viewLifecycleOwner, Observer { msg ->
            msg?.let {
                Snackbar.make(requireView(), it, Snackbar.LENGTH_SHORT).apply {
                    setAnchorView(R.id.cardViewCep)
                    show()
                }
            }
        })

        viewModel.toast.observe(viewLifecycleOwner, Observer { msg ->
            msg?.onToastShow(requireContext())
        })

        viewModel.address.observe(viewLifecycleOwner, Observer {
            it?.let {
                viewModel.onFillFields(it)
            }
        })

        viewModel.connectionIsActive.observe(viewLifecycleOwner, Observer {
            it?.let {
                getString(R.string.offline_connection).onToastShow(requireContext())
            }
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnSearchPostalCode.setOnClickListener {
            if (Connection.isActive(view.context).not()) {
                viewModel.onConnectionIsActive(false)
                return@setOnClickListener
            }

            editTextPostalCode.editableText.toString().let { postalCode ->
                if (postalCode.isEmpty() || postalCode.length < LENGHT_POSTAL_CODE) {
                    if (postalCode.isEmpty()) {
                        viewModel.onSnackbarShow(getString(R.string.fill_postal_code))
                    } else {
                        viewModel.onSnackbarShow(getString(R.string.typeIt_postal_code_full))
                    }
                    return@setOnClickListener
                }
                onHideKeyboard()
                viewModel.onCleanFields()
                viewModel.onProgressBarShow(true)
                viewModel.onSearchAddress(postalCode)
            }
        }
    }

    override fun onStart() {
        super.onStart()

        this.setTitle(getString(R.string.search_by_postal_code))
    }
}
