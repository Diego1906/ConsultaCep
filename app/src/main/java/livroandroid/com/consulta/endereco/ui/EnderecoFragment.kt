package livroandroid.com.consulta.endereco.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.fragment_endereco.*
import livroandroid.com.consulta.R
import livroandroid.com.consulta.util.setTitle

/**
 * A simple [Fragment] subclass.
 */
class EnderecoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_endereco, container, false)
    }

    override fun onStart() {
        super.onStart()

        this.setTitle(getString(R.string.consulta_por_endereco))

        val estados = resources.getStringArray(R.array.Estados)
        if (spinner_estado != null) {
            val adapter =
                ArrayAdapter(this.requireContext(), android.R.layout.simple_spinner_dropdown_item, estados)
            spinner_estado.adapter = adapter
        }
    }
}
