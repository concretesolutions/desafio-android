package br.com.bernardino.githubsearch.di

import br.com.bernardino.githubsearch.database.RepositoriesDatabase
import org.koin.dsl.module

val databaseModule = module {

    single { RepositoriesDatabase.getDatabase(get()) }

    single { (get() as RepositoriesDatabase).reposDao }
}
