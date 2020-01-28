package livroandroid.com.consultacep.cep.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_cep.*
import livroandroid.com.consultacep.R
import livroandroid.com.consultacep.cep.viewmodel.CepViewModel
import livroandroid.com.consultacep.network.RetroFitConfig
import livroandroid.com.consultacep.repository.AdressRepository

/**
 * A simple [Fragment] subclass.
 */
class CepFragment : Fragment() {

    private lateinit var viewModel: CepViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_cep, container, false)

        val application = requireNotNull(this.activity).application

        val repository = AdressRepository(RetroFitConfig())

        viewModel = ViewModelProviders
            .of(this, CepViewModel.FACTORY(repository))
            .get(CepViewModel::class.java)

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

            viewModel.searchAdress(cep)
        }
    }
}
