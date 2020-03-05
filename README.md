# App Consulta Cep
O aplicativo possui duas telas sendo uma para consultar as informações por CEP e a outra por dados do endereço.

O projeto consome uma API REST para buscar os dados do endereço utilizando como parâmetro de entrada o número do CEP ou informações do endereço tais como: rua, cidade e estado( UF ).

A API utilizada foi a ViaCEP que se encontra no link: https://viacep.com.br/

No desenvolvimento do projeto foram utilizadas as tecnologias descritas abaixo:

- Linguagem de programação
  - 
  - Kotlin

- Componentes de Arquitetura
  -
  - Navigation - documentação: https://developer.android.com/guide/navigation
  - ViewModel - documentação: https://developer.android.com/topic/libraries/architecture/viewmodel#sharing
  - LiveData - documentação: https://developer.android.com/topic/libraries/architecture/livedata
  - DataBinding - documentação: https://developer.android.com/topic/libraries/data-binding
  - Lifecycle - documentação: https://developer.android.com/topic/libraries/architecture/lifecycle
  - Coroutines - documentação: https://developer.android.com/topic/libraries/architecture/coroutines
 
- Bibliotecas externas
  - 
  - Retrofit - é utilizado fazer requisições de serviços Web - documentação: https://square.github.io/retrofit/
  - Gson - é utilizado para fazer conversão de objetos em sua representação JSON - documentação: https://github.com/google/gson
  - Koin - é utilizado para fazer injeção de depedência - documentação: https://insert-koin.io/
    
- Bibliotecas internas
  -
  - RecyclerView - é utilizado para criar listas de informações, objetos, imagens etc... - documentação: https://developer.android.com/guide/topics/ui/layout/recyclerview
  - Material Design - é a orientação de código e projeto oficial do Google - documentação: https://material.io/
  
- Design de Arquitetura
  - 
  - MVVM - é o padrão de design de arquitetura de software que a Google indica para os novos desenvolvimento. Os novos componentes de arquitetura já são lançados com suporte a esse tipo de padrão - mais informações: https://medium.com/involves-rocks/observa%C3%A7%C3%B5es-sobre-mvp-mvvm-e-outras-letras-c00656058f44

