package livroandroid.com.consulta

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.fragment_start.*
import livroandroid.com.consulta.util.setTitle


class StartFragment : Fragment(), BottomNavigationView.OnNavigationItemSelectedListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_start, container, false)
    }

    override fun onStart() {
        super.onStart()

        this.setTitle(getString(R.string.consulta))

        bottom_navigation.setOnNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_cep -> {
                this.findNavController().navigate(
                    StartFragmentDirections.actionStartFragmentToCepFragment()
                )
            }
            R.id.menu_endereco -> {
                this.findNavController().navigate(
                    StartFragmentDirections.actionStartFragmentToEnderecoFragment()
                )
            }
        }
        return true
    }
}
