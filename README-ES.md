# Crear una app de consulta a la API del [GitHub](https://github.com/)

Desarrollar una app para consultar la [API do GitHub](https://developer.github.com/v3/) y cargar los repositorios más populares del Java. Básese en el mockup fornecido: 

![Captura de tela de 2015-10-22 11-28-03.png](https://bitbucket.org/repo/7ndaaA/images/3102804929-Captura%20de%20tela%20de%202015-10-22%2011-28-03.png)

### **Debe contener**

- **Lista de repositorios**. Ejemplo de llamada en la API: `https://api.github.com/search/repositories?q=language:Java&sort=stars&page=1` 
  * Paginación en la pantalla de lista, con endless scroll / scroll infinito (incrementando el parámetro `page`).
  * Cada repositorio debe presentar Nombre del repositorio, Descripción del Repositorio, Nombre / Foto del autor, Cantidad de Stars, Cantidad de Forks
  * Al tocar em um articulo, debe llevar a la lista de Pull Requests del repositorio
- **Pull Requests de un repositorio**. Ejemplo de llamada en la API: `https://api.github.com/repos/<criador>/<repositório>/pulls` 
  * Cada artículo de la lista debe presentar Nombre / Foto del autor del Pull Request, Título del  Pull Request, Data do Pull Request e Body do Pull Request
  * Al tocar en un artículo, debe abrir en el navegador la pantalla del Pull Request seleccionado

### **La solución DEBE contener**

* Sistema de build Gradle
* Mapeo JSON -> Objeto (GSON / Jackson / Moshi / etc)
* Material Design

### **Logra  + pontos si se presenta**

* Framework para comunicación con API
* Pruebas en el proyecto (de unidad e por pantalla)
* Pruebas funcionales (que naveguen por la aplicación cómo casos de uso)
* Cache de imágenes e de la API
* Soportar cambios de orientación de las pantallas sin perder el estado

### **Sugerências**

La sugerencia de librerías que sigue es solamente un *guideline*. Quédate tranquilo en usar otras que nos sorprenda. Lo que cuenta es lograr los objetivos macro. =) 

* Retrofit | Volley
* Picasso | Fresco | Glide
* Espresso | Robotium | Robolectric

### **OBSERVACIONES**

La foto del mockup es meramente ilustrativa.   

### **Submissión**

El candidato debe implementar la solución y enviar un pull request para este repositorio con la solución. 

El processo de Pull Request funciona así: 

1. Candidato hace *fork* de ese repositorio (¡no lo clones directamente!)
2. Haga tú proyecto en ese *fork*.
3. Haga *commit* y vas a subir las modificaciones para **TÚ** *fork*.
4. Por la interface del Bitbucket, vas a enviar un *Pull Request*.

Si caso sea posible, dejar el *fork* público para tornar más simple inspeccionar el código. 

### **ATENCIÓN**

No intentes hacer PUSH directamente para ESTE repositorio! 