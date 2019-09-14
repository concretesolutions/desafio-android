package com.silvioapps.githubkotlin.di.modules

import android.app.Application
import android.content.Context
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.silvioapps.githubkotlin.features.details.adapters.DetailsListAdapter
import com.silvioapps.githubkotlin.features.details.fragments.DetailsFragment
import com.silvioapps.githubkotlin.features.details.models.DetailsModel
import com.silvioapps.githubkotlin.features.list.models.ListModel
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

    @Provides
    fun providesMutableListOfDetailsModel(): MutableList<DetailsModel>{
        return mutableListOf<DetailsModel>()
    }

    @Provides
    fun providesLinearLayoutManager(context: Context): LinearLayoutManager{
        return LinearLayoutManager(context)
    }

    @Provides
    fun providesDefaultItemAnimator(): DefaultItemAnimator {
        return DefaultItemAnimator()
    }

    @Provides
    fun providesMutableListOfListModel(): MutableList<ListModel>{
        return mutableListOf<ListModel>()
    }

    @Provides
    fun providesDetailsListAdapter(list: MutableList<DetailsModel>, viewClickListener: DetailsFragment.DetailsViewClickListener): DetailsListAdapter {
        return DetailsListAdapter(list, viewClickListener)
    }

    @Provides
    fun providesViewClickListener(): DetailsFragment.DetailsViewClickListener {
        return DetailsFragment.DetailsViewClickListener.DetailsViewClickListenerImpl()
    }
}

