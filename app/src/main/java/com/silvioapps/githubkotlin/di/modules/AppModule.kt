package com.silvioapps.githubkotlin.di.modules

import android.app.Application
import android.content.Context
import com.silvioapps.githubkotlin.features.list.models.ListModel
import com.silvioapps.githubkotlin.features.shared.listeners.ViewClickListener
import javax.inject.Singleton
import dagger.Module
import dagger.Provides

@Module(includes = [ActivitiesModule::class, FragmentsModule::class])
class AppModule{

    @Provides
    @Singleton
    fun providesContext(application: Application): Context{
        return application
    }

    /*@Provides
    @Singleton
    fun providesListOfListModel(obj:  List<ListModel>):  List<ListModel>{
        return obj
    }

    @Provides
    @Singleton
    fun providesViewClickListener(obj: ViewClickListener): ViewClickListener {
        return obj
    }*/
}

