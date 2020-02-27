package livroandroid.com.consulta.di

import livroandroid.com.consulta.network.RetroFitConfig
import livroandroid.com.consulta.repository.AdressRepository
import livroandroid.com.consulta.viewmodel.AdressViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val appModuleViewModel = module {
    factory { AdressViewModel(get(), androidApplication()) }
    single { AdressRepository(get()) }
    single { RetroFitConfig() }
}
