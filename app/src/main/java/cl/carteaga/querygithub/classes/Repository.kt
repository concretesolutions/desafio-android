package cl.carteaga.querygithub.classes

data class Repository(
    var name: String,
    var description: String,
    var numberStar: Int,
    var numberForks: Int,
    var author: Author
)
