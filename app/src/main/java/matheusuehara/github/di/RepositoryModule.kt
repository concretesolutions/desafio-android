package matheusuehara.github.di

import matheusuehara.github.data.request.Repository
import org.koin.dsl.module

val repositoryModule = module {

    factory { Repository(get())}

}