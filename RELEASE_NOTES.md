# app_desafio_android

Funcionalidades desenvolvidas: As funcionalidades foram implementadas apenas para versão smartphone portrait e landcaspe.

  	Desenvolveu-se as funcionalidades de:
  
  	Lista de repositório: Responsável por realizar o login no web service, após o primeiro acesso o token do usuário é salvo localmente no shared preferences para posteriormente ser possível o acesso offline;
  
  	Lista de pull request: Responsável por exibir a lista de cachorros, as imagens são salvas no cache, ou seja, mesmo sem conexão as imagens podem ser visualizadas (somente as imagens que foram baixadas);
  
  	  
 	 Cache de Api e imagens: Responsável por realizar o cache das imagens, para essa funcionalidade ser possível, a lista de urls dos cachorros, foram salvas na base de dados do aplicativo.
  
  	Aplicou-se testes na tela de login, onde foi validado email vazio, email inválido, e se os componentes da view estavam visiveis no estado inicial da activity, e também se o token foi salvo ou não no shared preferences. Para a execução dos testes é necessário estar na tela de login, se necessário deve-se fazer o logout.
		

Arquitetura utilizada:

  	Utilizou-se a arquitetura de componentes do google, com as camadas de:
  
  	View: Contém as activitys e fragments;
  	Viewmodel: Camada responsável por fazer a ligação da camada de view com a camada de repository;
  	Repository: Camada responsável por acessar a base de dados e o webservice;   	
	
  

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
  
  

  

