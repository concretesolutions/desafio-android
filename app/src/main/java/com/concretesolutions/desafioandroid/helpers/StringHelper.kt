package com.concretesolutions.desafioandroid.helpers

class StringHelper {
    companion object {
        fun makeShortDescription(description: String?): String {
            return if(description.isNullOrEmpty()) "--"
            else if(description.length > 70) description.substring(0, 70) + "..."
            else description
        }

        fun prepareDescription(description: String?): String {
            return  if(description.isNullOrEmpty()) "--"
            else description
        }
    }
}