# Criar um aplicativo de consulta na api do [Dribbble](https://dribbble.com)#

Criar um aplicativo para consultar a [Dribbble API](http://developer.dribbble.com/v1/) e trazer os shots + populares . Basear-se no mockup fornecido:

![Screen Shot 2014-10-09 at 3.42.06 PM.png](https://bitbucket.org/repo/bApLBb/images/3039998141-Screen%20Shot%202014-10-09%20at%203.42.06%20PM.png)

### **Deve conter** ###

* Lista de shots. Exemplo de chamada na API: http://api.dribbble.com/shots/popular?page=1
* Paginação na tela de lista (aumentando o parâmetro "page").
* Detalhe de um shot. Exemplo de chamada na API: http://api.dribbble.com/shots/1757954
* Tela de detalhe de um shot deve conter autor com foto.
* Carregamento assíncrono das imagens e sob demanda

### **Ganha + pontos se conter** ###

* Sistema de build e gestão de dependências no projeto. Ex: [Gradle, Maven]
* Mapeamento JSON -> Objeto. 
* Framework para comunicação com API.
* Testes no projeto (unitários e por tela). 
* Testes funcionais (que naveguem pelo aplicativo como casos de uso). 
* Cache de imagens e da API. 

### **Sugestões** ###

As sugestões de bibliotecas fornecidas são só um guideline, sintam-se a vontade para usar diferentes e nos surpreenderem. O importante de fato é que os objetivos macros sejam atingidos. =)

* AndroidAnnotations
* Retrofit | Volley | Spring-Android
* Picasso | Universal Image Loader
* Espresso | Robotium

### **OBS** ###

A foto do mockup é meramente ilustrativa, não existe necessidade de fazer share em redes sociais. 
Dito isso, consideraremos como um plus caso seja feito.  


### **Processo de submissão** ###
O candidato deverá implementar a solução e enviar um pull request para este repositório com a solução.

O processo de Pull Request funciona da seguinte maneira:
1. Candidato fará um fork desse repositório (não irá clonar direto!)
2. Fará seu projeto nesse fork.
3. Commitará e subirá as alterações para o SEU fork.
4. Pela interface do Bitbucket, irá enviar um Pull Request.

### **ATENÇÃO** ###
Não se deve tentar fazer o PUSH diretamente para ESTE repositório!!!