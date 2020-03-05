package livroandroid.com.consulta.util

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

fun Fragment.setTitle(title: String) {
    (activity as? AppCompatActivity)?.supportActionBar?.title = title
}

fun Fragment.onHideKeyboard() {
    val imm = ContextCompat.getSystemService(requireContext(), InputMethodManager::class.java)
    imm?.hideSoftInputFromWindow(requireView().windowToken, 0)
}

fun String.onToastShow(context: Context) {
    Toast.makeText(context, this, Toast.LENGTH_LONG).show()
}

fun String.onSnackBarShow(view: View) {
    Snackbar.make(view, this, Snackbar.LENGTH_SHORT).show()
}