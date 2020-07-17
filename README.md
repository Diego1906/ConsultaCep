# App Consulta Cep

O aplicativo consome uma API REST para buscar os dados do endereço utilizando como parâmetro o número do CEP ou informações do endereço tais como: rua, cidade e estado (UF).

API utilizada [ViaCEP](https://viacep.com.br/).

**O aplicativo possui duas telas e elas são:**
  - 1º Tela consulta as informações por CEP;
  - 2º Tela consulta as informações por dados do endereço;

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
  - [ConstraintLayout](https://developer.android.com/training/constraint-layout) permite você criar layouts grandes e complexos com uma hierarquia de visão plana (sem grupos de visão aninhados)
  
- Design de Arquitetura
  - 
  - [MVVM](https://developer.android.com/jetpack/docs/guide) é o padrão de design de arquitetura de software que a Google indica para os novos desenvolvimento. Os novos componentes de arquitetura já são lançados com suporte a esse tipo de padrão
  
- Padrões de Projeto (Design Patterns)
  - 
  - [Repository](https://proandroiddev.com/the-real-repository-pattern-in-android-efba8662b754) é estratégia para abstrair o acesso aos dados. Ele é composto pelo código em uma aplicação que lida com o armazenamento e a recuperação de dados. [Mais detalhes](https://makingloops.com/why-should-you-use-the-repository-pattern/).
  - [Dependency injection](https://pt.wikipedia.org/wiki/Inje%C3%A7%C3%A3o_de_depend%C3%AAncia) é um padrão de desenvolvimento de programas de computadores utilizado quando é necessário manter baixo o nível de acoplamento entre diferentes módulos de um sistema.
  - [Adapter](https://pt.wikipedia.org/wiki/Adapter) o padrão Adapter converte a interface de uma classe para outra interface que o cliente espera encontrar, "traduzindo" solicitações do formato requerido pelo usuário para o formato compatível com o a classe adapter e as redirecionando. Dessa forma, o Adaptador permite que classes com interfaces incompatíveis trabalhem juntas
  
- Princípios do SOLID utilizados
  - 
  - [Single Responsibility](https://en.wikipedia.org/wiki/Single-responsibility_principle) (SRP) principio da Responsabilidade Única
  - [Interface Segregation](https://www.webcitation.org/6AL2qqIGg?url=http://www.objectmentor.com/resources/articles/isp.pdf) (ISP) princípio da Segregação da Interface
  - [Dependency Inversion](https://web.archive.org/web/20110714224327/http://www.objectmentor.com/resources/articles/dip.pdf) (DIP) princípio da inversão da dependência
