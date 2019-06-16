package isaacborges.com.projetoconcrete.retrofit.services;

import isaacborges.com.projetoconcrete.dto.PullRequestSync;
import isaacborges.com.projetoconcrete.dto.RepositoriosSync;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RepositoriosService {

    @GET("search/repositories")
    Call<RepositoriosSync> buscaRepositorios(
            @Query("q") String linguagem,
            @Query("sort") String ordem,
            @Query("page") int numeroDaPagina);

    @GET("repos/{autor}/{repositorio}/pulls")
    Call<PullRequestSync> buscaPullRequestsDoRepositorio(@Path("autor") String nomeAutor, @Path("repositorio") String nomeRepositorio);

}
