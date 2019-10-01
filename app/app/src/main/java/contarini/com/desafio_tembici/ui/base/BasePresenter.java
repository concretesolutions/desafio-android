package contarini.com.desafio_tembici.ui.base;

public interface BasePresenter<V extends BaseView> {

    void attachView(V mvpView);

    void detachView();
}
