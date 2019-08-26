package br.com.devdiegopirutti.mainactivity.presenter;


import br.com.devdiegopirutti.mainactivity.models.BaseResponse;
import br.com.devdiegopirutti.mainactivity.retrofit.APIService;
import br.com.devdiegopirutti.mainactivity.retrofit.RetrofitService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityPresenter {

    private MainActivityInterface view;

    private APIService apiService = RetrofitService.createRetrofit();

    public MainActivityPresenter(MainActivityInterface view) {
        this.view = view;
    }

    public void pegarDados(int pagina){
        apiService.obterListaUsuarios(pagina, "stars", "language:Java")
                .enqueue(new Callback<BaseResponse>() {
                    @Override
                    public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                        if(response.isSuccessful()){
                            view.apresentarDados(response.body().getItems());
                        }
                    }

                    @Override
                    public void onFailure(Call<BaseResponse> call, Throwable t) {
                        view.aconteceuUmErro();
                        t.printStackTrace();
                    }
                });
    }
}
