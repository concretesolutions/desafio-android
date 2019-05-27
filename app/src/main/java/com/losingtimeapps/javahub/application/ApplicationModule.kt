package com.losingtimeapps.javahub.application

import android.content.Context
import com.losingtimeapps.domain.boundary.BaseScheduler
import com.losingtimeapps.javahub.common.di.qualifiers.ForApplication
import com.losingtimeapps.presentation.schedule.ScheduleImp
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule {

    @ForApplication
    @Provides
    internal fun provideApplicationContext(app: JavaHubApplication): Context {
        return app.applicationContext
    }

    @Provides
    fun provideScheduler(): BaseScheduler = ScheduleImp()

}
