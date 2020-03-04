package livroandroid.com.consulta.di

import livroandroid.com.consulta.network.RetroFitConfig
import livroandroid.com.consulta.network.IService
import livroandroid.com.consulta.repository.AddressRepository
import livroandroid.com.consulta.repository.IRepositoryAddress
import livroandroid.com.consulta.viewmodel.AddressViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { AddressViewModel(get(), androidApplication()) }
    single<IRepositoryAddress> { AddressRepository(get()) }
    single<IService> { RetroFitConfig() }
}
