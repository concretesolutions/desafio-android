package com.bassul.mel.app.feature.repositoriesList

import com.bassul.mel.app.domain.Item
import com.bassul.mel.app.domain.Owner
import com.bassul.mel.app.feature.repositoriesList.repository.model.ItemResponse
import com.bassul.mel.app.feature.repositoriesList.repository.model.RepositoriesListResponse

class RepositoryMock {
    companion object{
        fun listItemMock() : ArrayList<Item>{
            val list: ArrayList<Item> = ArrayList()
            list.add(itemMock())
            return list
        }

        private fun itemMock() : Item {
            return Item(1, "Name", ownerMock(), "300", "200", "description", "url")
        }

        private fun ownerMock() : Owner {
            return Owner("logn", 2, "url")
        }

        fun errorMessageRepositoryMock() : Int{
            return 2
        }

  /*      private fun listResponseMock() : ArrayList<ItemResponse>{
            val list : ArrayList<ItemResponse> = ArrayList()
            list.add(itemResponseMock())
            return list
        }*/

        /*fun repositoriesListReponseMock() : RepositoriesListResponse {
            return RepositoriesListResponse(23, false, listResponseMock(), "message")
        }*/

  /*      private fun itemResponseMock() : ItemResponse{
            return ItemResponse(1, "Name", ownerMock(), "300", "200", "description", "url")
        }*/
    }
}