package com.ruiderson.desafio_android.models

import junit.framework.Assert.assertEquals
import org.junit.Test

class RepositoryCacheTest{


    @Test
    fun `initialize and compare cache`(){
        val repository = Repository(50, "Unit Test", "Unit Test description", 10, 5, RepositoryOwner("fake url", "Test"))
        val cache = RepositoryCache(repository, 1)
        assertEquals(repository.id, cache.id)

        val restoredRepository = cache.getRepository()
        assertEquals(repository.id, restoredRepository.id)
        assertEquals(repository.owner.login, restoredRepository.owner.login)
    }


}