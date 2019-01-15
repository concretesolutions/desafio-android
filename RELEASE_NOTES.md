# app_desafio_android

Funcionalidades desenvolvidas: As funcionalidades foram implementadas apenas para versão smartphone portrait e landcaspe.

  	Desenvolveu-se as funcionalidades de:
  
  	Lista de repositório: Responsável por exibir a lista dos repositórios mais populares de Java;
  	Lista de pull request: Responsável por exibir a lista de pull request de um repositório selecionado na tela que lista os repositórios;
  	  
 	 Cache de Api e imagens: Responsável por realizar o cache das imagens, para essa funcionalidade ser possível, foi adicionado um interceptador de cache no retrofit;
	 
	 Visualização do pull request no browser: Responsável por exibir a página do Pull Request selecionado na tela que lista os pull request.
	 Aplicou-se os seguintes testes:
	 	Na tela lista de repositório: foi verificado se a lista de repositório é visível após a requisição com o servidor, verificou-se também se o nome do repositório é visivél após requisição com o servidor e por último validou-se se ao tocar em um item, a lista de Pull Requests do repositório é exibida.
		Na tela lista de pull request: foi verificado se a lista de pull request é exibida após a requisição com o servidor, verificou-se também se o nome do usuário é visivél após requisição com o servidor e por último validou-se se ao clicar em um pull request, o browser com a página do request em questão é aberta.
  		

Arquitetura utilizada:

  	Utilizou-se a arquitetura de componentes do google, com as camadas de:
  
  	View: Contém as activitys;
  	Viewmodel: Camada responsável por fazer a ligação da camada de view com a camada de repository;
  	Repository: Camada responsável por acessar o webservice;   	
	
  

Bibliotecas utilizadas:

 Além das bibliotecas de design do google, foram utilizadas as seguintes bibliotecas:  
 
  

  	Dagger2: Utilizada para a injeção de independencia do aplicativo;
  
 	 Gson: Utilizada para a converção dos objetos;
  
  	Retrofit: Utilizada para as requisições ao web service;
  
  	Picasso: Utilizada para o download e cache das imagens;
  
  	Espresso: Utilizada para a aplicação dos testes.
	
	
  
  
Requisitos minimos:
  
  	Android Studio 3.2.2;  
  	Versão mínima so sdk: 22.
  
  

  

