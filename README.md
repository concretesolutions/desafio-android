# Descrição
Um aplicativo Android que consume dados da API GitHub e os exibe para os usuários

## Telas

- Tela de repositórios: exibe todos os repositórios mais bem avaliados em uma lista infinita. 
É possível pressionar qualquer repositório para selecioná-lo e deletá-lo localmente ou apenas clicar
em um repositório para abrir a próxima tela.

- Tela de pull requests: exibe todos os pull requests de um específico repositório que foi selecionado
na tela anterior

## Requisitos

- Java 7
- IDE Android Studio
- Dispositivo Android ou AVD(Dispositivo Android Virtual)

## Executando

- Baixe ou clone o projeto: git clone https://github.com/bedrickprokop/githubrepos.git
- Abra o Android Studio e aponte para o projeto
- Espere o gradle carregar as dependencias
- Escolha o flavor(serão descritos no próximo tópico)
- Plugue seu dispositivo android com a opção "Developer options" habilitada e o checkbox "USB debugging" marcado ou crie um AVD.
- Run

## Flavors ou sabores

Foi criado dois flavors para usar durante o desenvolvimento e teste. Um para testar a aplicação com dados
mockados e o outro para testar com os dados reais.

- Mock: Abra o menu lateral "Build Variants" e escolha na seção "Build Variant" o flavor "mockDebug". 
Neste flavor você pode testar a aplicação sem conectar na internet apenas usando dados mockados.

- Prod: Abra o menu lateral "Build Variants" e escolha na seção "Build Variant" o flavor "prodDebug" ou "prodRelease". 
Nestes flavors você pode executar a aplicação com dados reais.

## Testes unitários

- Nos testes unitários não é preciso plugar o celular no computador via USB, pois eles executam no seu próprio computador. 
- Eles estão dentro da pasta "test" e você pode executá-los clicando com o botão direito na classe e selecionando a opção 
"Run".

## Testes de tela

- Para este tipo de teste, você deve estar com o celular ligado no USB ou usar o AVD
- Você pode executar os testes de tela clicando com o botão direito nas classes que estão dentro do pacote "androidTest"
e depois selecionando a opção "Run".

Observação: Desbloqueie seu celular para ver os testes executando e use o flavor "mock" para este tipo de teste.


## Especificações da aplicação

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

## Bibliotecas e Frameworks

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

