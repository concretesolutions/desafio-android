# GitHub Repos #

Este é um aplicativo que lista os repositórios mais populares do GitHub com a linguagem Java.

A tela inicial mostra a lista citada acima e, ao clicar em algum repositório é aberta a lista de Pull Requests deste.

Para abrir o Pull Request, basta clicar no item da lista que o aplicativo abrirá a página do repositório no Github em seu navegador.

## Algumas screenshots
![light](https://github.com/jsouza678/concrete-desafio/blob/master/screenshots/home_light.png)
![dark](https://github.com/jsouza678/concrete-desafio/blob/master/screenshots/home_dark.png)
![pulls_dark](https://github.com/jsouza678/concrete-desafio/blob/master/screenshots/pulls_dark.png)
![error](https://github.com/jsouza678/concrete-desafio/blob/master/screenshots/error.png)

## Ambiente de instalação
* 1: Instale o Android Studio;
* 2: Abra a aplicação;
* 3: Sincronize o projeto;
* 4: Rode o aplicativo em um simulador ou em um device externo.

## API
<p>A API utilizada é a GitHub Api.</p>
(https://developer.github.com/v3/)

** Automação **
Ktlint - a task valida se o padrão do código está de acordo com o lint.
O `./gradlew ktlint` realiza a verificação de todos os componentes do projeto, e retorna o resultado.

KtlintFormat - esta tarefa modifica o código para que ele siga o padrão do lint.
O `./gradlew ktlintFormat` roda uma rotina que formata o código de acordo com o máximo que o lint pode fazer de modificações para que o código esteja no seu padrão.

** Arquitetura **
 A aplicação busca o desacoplamento e a escalabilidade em sua arquitetura, fazendo uso do Clean Architecture e do MVVM com Modularização.

# Principais dependências #

**Coroutines** - _lidando com threads e assincronismo_
 <p>Abordagem sugerida pela Google e com um bom funcionamento com o Live Data, faz bom uso das threads do dispositivo, melhorando a performance da aplicação. No aplicativo também foi utilizado o Flow na tela principal.</p>

** Paging Library 3.0 **
<p> A Paging Library 3.0 em sua versão nova, permite que os dados carregados da Api sejam salvos no banco de dados e carregados de acordo com a página em questão.
O Remote Mediador age como uma camada que verifica a página atual, próxima e anterior para determinar a próxima chamada à Api, assim como a operação de inserção dos dados no Banco de Dados.</p>

**Room** - _persistência de dados_
 <p>Camada de abstração sobre o SQLite, o Room é um facilitador para persistir dados no banco do aparelho.
 É importante ressaltar que a utilização do Room e Coroutines necessita de uma forma de verificar as queries do banco de dados, já que elas devem ser feitas de forma async.
 No RepoCatalog foi diferente, já que, como a Paging Library 3.0 possibilita retornar um Flow, foi utilizada a lifecycle scope para receber os dados na activity e mandar para o adapter levando em conta o seu ciclo de vida.

**Retrofit** - _requisições HTTP_
 <p>Retrofit é a biblioteca mais difundida por encapsular e lidar com requisições HTTP, além de possuir uma fácil implementação. Com o uso do OkHttp associado ao retrofit, ele "desbloqueia" o potencial do retrofit.</p>

**Material Design** - _layout intuitivo e clean_
 <p>O aplicativo segue os padrões do MaterialDesign para uma melhor experiência do usuário em sua utilização.</p>

**Koin** _injeção de dependência_
 <p>Biblioteca escolhida por sua simples implementação an injeção de dependência.</p>

## O que eu gostaria de ter feito

* _criado testes unitários;_

* _criado testes de ui;_

##

# Criar um aplicativo de consulta a API do [GitHub](https://github.com)#

Criar um aplicativo para consultar a [API do GitHub](https://developer.github.com/v3/) e trazer os repositórios mais populares de Java. Basear-se no mockup fornecido:

![Captura de tela de 2015-10-22 11-28-03.png](https://bitbucket.org/repo/7ndaaA/images/3102804929-Captura%20de%20tela%20de%202015-10-22%2011-28-03.png)

### **Deve conter** ###

- __Lista de repositórios__. Exemplo de chamada na API: `https://api.github.com/search/repositories?q=language:Java&sort=stars&page=1`
  * Paginação na tela de lista, com endless scroll / scroll infinito (incrementando o parâmetro `page`).
  * Cada repositório deve exibir Nome do repositório, Descrição do Repositório, Nome / Foto do autor, Número de Stars, Número de Forks
  * Ao tocar em um item, deve levar a lista de Pull Requests do repositório
- __Pull Requests de um repositório__. Exemplo de chamada na API: `https://api.github.com/repos/<criador>/<repositório>/pulls`
  * Cada item da lista deve exibir Nome / Foto do autor do PR, Título do PR, Data do PR e Body do PR
  * Ao tocar em um item, deve abrir no browser a página do Pull Request em questão

### **A solução DEVE conter** ##
* Sistema de build Gradle
* Mapeamento JSON -> Objeto (GSON / Jackson / Moshi / etc)
* Material Design

### **Ganha + pontos se conter** ###

* Framework para comunicação com API
* Testes no projeto (unitários e por tela)
* Testes funcionais (que naveguem pelo aplicativo como casos de uso)
* Cache de imagens e da API
* Suportar mudanças de orientação das telas sem perder estado

### **Sugestões** ###

As sugestões de bibliotecas fornecidas são só um guideline, sintam-se a vontade para usar diferentes e nos surpreenderem. O importante de fato é que os objetivos macros sejam atingidos. =)

* Retrofit | Volley 
* Picasso | Universal Image Loader | Glide
* Espresso | Robotium | Robolectric

### **OBS** ###

A foto do mockup é meramente ilustrativa.  


### **Processo de submissão** ###

O candidato deverá implementar a solução e enviar um pull request para este repositório com a solução.

O processo de Pull Request funciona da seguinte maneira:

1. Candidato fará um fork desse repositório (não irá clonar direto!)
2. Fará seu projeto nesse fork.
3. Commitará e subirá as alterações para o __SEU__ fork.
4. Pela interface do Github, irá enviar um Pull Request.

Se possível deixar o fork público para facilitar a inspeção do código.

### **ATENÇÃO** ###

Não se deve tentar fazer o PUSH diretamente para ESTE repositório!
