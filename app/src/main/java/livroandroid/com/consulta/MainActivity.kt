package livroandroid.com.consulta

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import livroandroid.com.consulta.di.appModuleViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.KoinComponent
import org.koin.core.context.startKoin

class MainActivity : AppCompatActivity(), KoinComponent {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startKoin {
            androidContext(application)
            modules(appModuleViewModel)
        }
    }
}
