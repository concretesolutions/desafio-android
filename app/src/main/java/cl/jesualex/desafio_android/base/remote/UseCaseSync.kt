package cl.jesualex.desafio_android.base.remote

/**
 * Created by jesualex on 21-03-19.
 */
abstract class UseCaseSync<T, U> {
    protected abstract fun execute(param: U?)

    protected abstract fun execute(): T
}
