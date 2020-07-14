package com.bassul.mel.app.feature.repositoriesList

import com.bassul.mel.app.domain.Item
import com.bassul.mel.app.domain.Owner

class RepositoryMock {
    companion object{
        fun listItemMock() : ArrayList<Item>{
            var list: ArrayList<Item> = ArrayList()
            list.add(itemMock())
            return list
        }

        fun itemMock() : Item {
            return Item(1, "Name", ownerMock(), "300", "200", "description", "url")
        }

        fun ownerMock() : Owner {
            return Owner("logn", 2, "url")
        }

        fun errorMessageRepositoryMock() : Int{
            return 2
        }
    }
}