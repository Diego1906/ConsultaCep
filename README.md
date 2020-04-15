# App Consulta Cep
O aplicativo possui duas telas sendo uma para consultar as informações por CEP e a outra por dados do endereço.

O projeto consome uma API REST para buscar os dados do endereço utilizando como parâmetro de entrada o número do CEP ou informações do endereço tais como: rua, cidade e estado( UF ).

A API utilizada foi a [ViaCEP](https://viacep.com.br/)

**No desenvolvimento do projeto foram utilizadas as tecnologias descritas abaixo**

- Linguagem de Programação
  - 
  - Kotlin

- Componentes de Arquitetura
  -
  - Documentação
    - 
    - [Navigation](https://developer.android.com/guide/navigation)
    - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel#sharing)
    - [LiveData](https://developer.android.com/topic/libraries/architecture/livedata)
    - [DataBinding](https://developer.android.com/topic/libraries/data-binding)
    - [Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle)
    - [Coroutines](https://developer.android.com/topic/libraries/architecture/coroutines)
 
- Bibliotecas Externas
  - 
  - [Retrofit](https://square.github.io/retrofit/) é utilizada para fazer requisições HTTP a serviços Web
  - [Gson](https://github.com/google/gson) é utilizada para fazer conversão de objetos em sua representação JSON
  - [Koin](https://insert-koin.io/) é utilizada para aplicar injeção de depedência
    
- Bibliotecas Internas
  -
  - [RecyclerView](https://developer.android.com/guide/topics/ui/layout/recyclerview) é utilizada para criar listas de informações, objetos, imagens que serão apresentadas na tela e etc.
  - [Material Design](https://material.io/) é a orientação de código e projeto oficial do Google
  
- Design de Arquitetura
  - 
  - [MVVM](https://medium.com/involves-rocks/observa%C3%A7%C3%B5es-sobre-mvp-mvvm-e-outras-letras-c00656058f44) é o padrão de design de arquitetura de software que a Google indica para os novos desenvolvimento. Os novos componentes de arquitetura já são lançados com suporte a esse tipo de padrão

