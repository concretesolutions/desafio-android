package cl.getapps.githubjavarepos.core.executor

import io.reactivex.Scheduler


interface PostExecutionThread {
    val scheduler: Scheduler
}
