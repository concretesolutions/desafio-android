package contarini.com.desafio_android.ui.base;

public interface BasePresenter<V extends BaseView> {

    void attachView(V mvpView);

    void detachView();
}
