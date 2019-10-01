package contarini.com.desafio_android.data.models

import java.io.Serializable

class PullRequestResponse(var url : String = "",
                               var id : Int = 0,
                               var node_id : String = "",
                               var number : Int = 0,
                               var state : String = "",
                               var locked : Boolean = false,
                               var title : String = "",
                               var user : UserResponse,
                               var body : String = "",
                               var created_at : String = "",
                               var updated_at : String = "",
                               var html_url : String = "") : Serializable


