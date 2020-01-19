package com.concretesolutions.desafioandroid.helpers

class StringHelper {
    companion object {
        fun makeShortDescription(description: String?): String {
            return if(description == null) "--"
            else if(description.length > 70) description.substring(0, 70) + "..."
            else description
        }
    }
}