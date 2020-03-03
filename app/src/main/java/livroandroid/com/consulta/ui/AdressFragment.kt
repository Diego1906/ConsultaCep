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
import kotlinx.android.synthetic.main.fragment_adress.*
import livroandroid.com.consulta.R
import livroandroid.com.consulta.databinding.FragmentAdressBinding
import livroandroid.com.consulta.ui.adapter.AdressListAdapter
import livroandroid.com.consulta.util.onHideKeyboard
import livroandroid.com.consulta.util.onSnackBarShow
import livroandroid.com.consulta.util.onToastShow
import livroandroid.com.consulta.util.setTitle
import livroandroid.com.consulta.viewmodel.AdressViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class AdressFragment : Fragment() {

    private val viewModel: AdressViewModel by viewModel()

    private lateinit var state: String
    private lateinit var city: String
    private lateinit var street: String

    private val adapterListAdress by lazy {
        AdressListAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<FragmentAdressBinding>(
            inflater, R.layout.fragment_adress, container, false
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

        viewModel.listAdress.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapterListAdress.submitList(it)
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

        setTitle(getString(R.string.consulta_por_endereco))
    }

    private fun onSetupRecyclerView() {
        recyclerView?.apply {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            adapter = adapterListAdress
        }
    }

    private fun onInitializeSpinner() {
        spinner_estado?.let {
            it.adapter = ArrayAdapter(
                this.requireContext(),
                android.R.layout.simple_spinner_dropdown_item,
                resources.getStringArray(R.array.Estados)
            )
        }
    }

    private fun onItemSelectedListenerSpinner() {
        spinner_estado.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(
                parent: AdapterView<*>?, view: View?, position: Int, id: Long
            ) {
                state = spinner_estado.selectedItem.toString()
            }
        }
    }

    private fun onSetOnClickListener() {
        btnSearchListAdress.setOnClickListener {
            if (onValidateFields().not()) {
                return@setOnClickListener
            }
            onHideKeyboard()
            viewModel.onCleanFields()
            viewModel.onProgressBarShow(true)
            viewModel.onSearchListAdress(state, city, street)
        }
    }

    private fun onValidateFields(): Boolean {
        edit_text_cidade.editableText.toString().let {
            if (it.isEmpty()) {
                return onShowMsg(getString(R.string.preencha_campo_cidade))
            }
            city = it
        }

        edit_text_rua.editableText.toString().let {
            if (it.isEmpty()) {
                return onShowMsg(getString(R.string.preencha_campo_rua))
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
