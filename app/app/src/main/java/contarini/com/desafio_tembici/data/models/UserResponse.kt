package contarini.com.desafio_tembici.data.models

import java.io.Serializable

data class UserResponse(var login : String = "",
                        var avatar_url : String = "") : Serializable