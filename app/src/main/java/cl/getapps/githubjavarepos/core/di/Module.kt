package cl.getapps.githubjavarepos.core.di

import cl.getapps.githubjavarepos.core.executor.JobExecutor
import cl.getapps.githubjavarepos.core.executor.PostExecutionThread
import cl.getapps.githubjavarepos.core.executor.ThreadExecutor
import cl.getapps.githubjavarepos.core.ui.UiThread
import org.koin.dsl.module.module

val threadModule = module {
    single { JobExecutor() as ThreadExecutor }
    single { UiThread() as PostExecutionThread }
}
