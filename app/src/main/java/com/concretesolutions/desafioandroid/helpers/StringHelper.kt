package com.concretesolutions.desafioandroid.helpers

class StringHelper {
    companion object {

        private const val NODESCRIPTION = "Sem descrição"

        fun makeShortDescription(description: String?): String {
            return if(description.isNullOrEmpty()) NODESCRIPTION
            else if(description.length > 70) description.substring(0, 70) + "..."
            else description
        }

        fun prepareDescription(description: String?): String {
            return  if(description.isNullOrEmpty()) NODESCRIPTION
            else description
        }
    }
}