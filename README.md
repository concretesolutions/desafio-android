# Description
Android application that consumes data from Github API and display them for users.

## Screens

- Repositories screen: displays all best ranked repositories in an infinity scroll list. 
You can press any repository to select it and the delete it locally or you can just press 
one repository to open the next screen.

- Pull requests screen: displays all pull requests from specific repository selected on the
previous screen.

## Requirements

- Java 7
- Android Studio
- Android Device or AVD(Android Virtual Device)

## Running

- Download or clone the project: git clone https://github.com/bedrickprokop/githubrepos.git
- Open your Android Studio and point to the project
- Wait until gradle load dependencies
- Choose one flavor(The flavors are described below)
- Plug your android device with the "Developer options" enabled and "USB debugging" checked or
create an AVD(Android Virtual Device)
- Run

## Flavors

I've created two flavors to use while developing and testing. One for test the application with 
mock data and the other to use real data.

- Mock: Open the "Build Variants" side menu and choose in "Build Variant" section the "mockDebug" flavor. 
In this flavor you can test the application without connect to internet just using mock values.

- Prod: Open the "Build Variants" side menu and choose in "Build Variant" section the "prodDebug" or "prodRelease" 
flavors. In these flavors you can run the application in real mode, with real data.

## Screen Tests

- You can run the screen tests by right clicking on the java classes inside the "androidTest" package and
selecting the option "Run ClassTestScreenName".

Note: Unlock your phone for you see the tests running in your phone and use the flavor "mock" for this kind of test

## Unit Tests

- They are in "test" folder and you can run them by right clicking on the test class and selecting 
"Run ClassTestName".

## App Specification

A aplicação foi escrita usado o padrão arquiterural MVP(Model View Presenter) e design patterns como Facade, Adapter e View holder.

No padrão MVP as 'activities' implementam um contrato onde são definidas as funcionalidades de tela como exibir lista de repositórios, alterar a action bar quando um item é pressionado, remover item, abrir nova tela, etc. Além disto, elas ouvem os eventos do usuário e para cada evento é chamada uma funcionalidade do seu respectivo 'presenter'.

Já as classes 'presenter' são as intermediadoras entre 'activities' e 'services', pois suas funções são executadas quando uma ação de usuário acontece. Ao receber um evento de usuário, a 'presenter' chama uma função de uma classe de 'service', recebe o retorno do mesmo e notifica a 'activity' deste retorno. Também implementam um contrato que define todas as suas funcionalidades.

Já as classes 'service' executam uma ação específica como buscar dados de um servidor de uma certa entidade. Elas também implementam um contrato que define suas funções.

Como exemplo, ao inicializar a aplicação as seguintes classes e métodos são executados na seguinte ordem:

1. RepositoriesActivity: onCreate()
2. RepositoriesPresenter: loadRepositoryList()
3. RepositoriesView: setProgressIndicator()
4. RepositoryServiceApi: search()
5. RepositoriesView: setProgressIndicator()
6. RepositoriesView: showRepositoryList()


## Principais classes e interfaces

- RepositoriesActivity: Activity inicial que mostra os repositórios do github

- RepositoriesAdapter: Classe que auxilia na construção da lista de repositórios

- RepositoriesPresenter: Classe que intermedia as ações do usuário e as respostas dos serviços

- RepositoriesContract: Interface de funcionalidades de view e ações do usuário

- RepositoryServiceApi: Interface que define as funcionalidades de acesso a dados do servidor.

- RepositoryServiceApiImpl: Classe que implementa as funcionalidades de acesso a dados do servidor

## Libraries and Frameworks

- Retrofit: Um cliente HTTP. Foi usado para fazer as requisições http
- OkHTTP: Um cliente HTTP. Foi usado para fazer cache de requisições http
- Picasso: Uma biblioteca para download e cache de imagens. 
- Guava: Uma biblioteca de utilitários da google.
- JUnit: Framework para testes unitários.
- Mockito: Framework para mock de objetos para testes.
- PowerMock: Biblioteca para mock e outros utilitários.
- Hamcrest: Biliboteca de utilitários para testes e mock.
- Espresso: Framework para criação de testes de tela
- ButterKnife: Framework para auxiliar no desenvolvimento, evitando códigos repetidos(boilerplate)

