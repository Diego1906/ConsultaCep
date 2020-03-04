package livroandroid.com.consulta.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_address.*
import livroandroid.com.consulta.R
import livroandroid.com.consulta.databinding.FragmentAddressBinding
import livroandroid.com.consulta.ui.adapter.AddressListAdapter
import livroandroid.com.consulta.util.onHideKeyboard
import livroandroid.com.consulta.util.onSnackBarShow
import livroandroid.com.consulta.util.onToastShow
import livroandroid.com.consulta.util.setTitle
import livroandroid.com.consulta.viewmodel.AddressViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddressFragment : Fragment() {

    private val viewModel: AddressViewModel by viewModel()

    private lateinit var state: String
    private lateinit var city: String
    private lateinit var street: String

    private val adapterListAddress by lazy {
        AddressListAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<FragmentAddressBinding>(
            inflater, R.layout.fragment_address, container, false
        )

        binding.viewModel = viewModel
        binding.setLifecycleOwner(viewLifecycleOwner)

        viewModel.snackbar.observe(viewLifecycleOwner, Observer {
            it?.onSnackBarShow(this.requireView())
        })

        viewModel.toast.observe(viewLifecycleOwner, Observer { msg ->
            msg?.let {
                view?.context?.let { context ->
                    it.onToastShow(context)
                }
            }
        })

        viewModel.listAddress.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapterListAddress.submitList(it)
            }
        })
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onSetupRecyclerView()

        onInitializeSpinner()

        onItemSelectedListenerSpinner()

        onSetOnClickListener()
    }

    override fun onStart() {
        super.onStart()

        setTitle(getString(R.string.search_by_address))
    }

    private fun onSetupRecyclerView() {
        recyclerView?.apply {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            adapter = adapterListAddress
        }
    }

    private fun onInitializeSpinner() {
        spinnerStates?.let {
            it.adapter = ArrayAdapter(
                this.requireContext(),
                android.R.layout.simple_spinner_dropdown_item,
                resources.getStringArray(R.array.states)
            )
        }
    }

    private fun onItemSelectedListenerSpinner() {
        spinnerStates?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(
                parent: AdapterView<*>?, view: View?, position: Int, id: Long
            ) {
                state = spinnerStates.selectedItem.toString()
            }
        }
    }

    private fun onSetOnClickListener() {
        btnSearchAddress.setOnClickListener {
            if (onValidateFields().not()) {
                return@setOnClickListener
            }
            onHideKeyboard()
            viewModel.onCleanFields()
            viewModel.onProgressBarShow(true)
            viewModel.onSearchListAddress(Triple(state, city, street))
        }
    }

    private fun onValidateFields(): Boolean {
        editTextCity.editableText.toString().let {
            if (it.isEmpty()) {
                return onShowMsg(getString(R.string.enter_city_name))
            }
            city = it
        }

        editTextStreet.editableText.toString().let {
            if (it.isEmpty()) {
                return onShowMsg(getString(R.string.enter_street_name))
            }
            street = it
        }
        return true
    }

    private fun onShowMsg(msg: String): Boolean {
        viewModel.onSnackbarShow(msg)
        return false
    }
}
