package livroandroid.com.consulta.di

import livroandroid.com.consulta.network.RetroFitConfig
import livroandroid.com.consulta.network.IService
import livroandroid.com.consulta.repository.AdressRepository
import livroandroid.com.consulta.repository.IRepository
import livroandroid.com.consulta.viewmodel.AdressViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModuleViewModel = module {
    viewModel { AdressViewModel(get(), androidApplication()) }
    single<IRepository> { AdressRepository(get()) }
    single<IService> { RetroFitConfig() }
}
