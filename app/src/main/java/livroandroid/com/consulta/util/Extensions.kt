package livroandroid.com.consulta.util

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

fun Fragment.setTitle(title: String) {
    (activity as AppCompatActivity).supportActionBar?.title = title
}

fun String.toastShow(context: Context) {
    Toast.makeText(context, this, Toast.LENGTH_LONG).show()
}

fun String.snackBarShow(view: View) {
    Snackbar.make(view, this, Snackbar.LENGTH_SHORT).show()
}