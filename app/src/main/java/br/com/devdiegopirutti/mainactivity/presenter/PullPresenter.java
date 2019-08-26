package br.com.devdiegopirutti.mainactivity.presenter;


import java.util.List;
import br.com.devdiegopirutti.mainactivity.models.BasePRResponse;
import br.com.devdiegopirutti.mainactivity.retrofit.APIService;
import br.com.devdiegopirutti.mainactivity.retrofit.RetrofitService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PullPresenter {

    private PullInterface view;
    private APIService apiService = RetrofitService.createRetrofit();

    public PullPresenter(PullInterface view) {this.view = view;}

    public void pegarDados(String owner, String repo){
        apiService.obterRepositorios( owner, repo)
                .enqueue(new Callback<List<BasePRResponse>>() {
                    @Override
                    public void onResponse(Call<List<BasePRResponse>> call, Response<List<BasePRResponse>> response) {
                    view.apresentarDados(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<BasePRResponse>> call, Throwable t) {
                        t.printStackTrace();
                        view.aconteceuUmErro();
                    }
                });
    }
}


