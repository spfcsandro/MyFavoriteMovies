package br.com.ilhasoft.myfavoritemovies.service;

import br.com.ilhasoft.myfavoritemovies.models.Filme;
import br.com.ilhasoft.myfavoritemovies.models.Search;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by SandroAndrade on 06/07/2016.
 */
public interface OMDBAPIService {

    //http://www.omdbapi.com/?s=Batman
    //http://www.omdbapi.com/?t=Batman&y=&plot=short&r=json

    public static final String BASE_URL = "http://www.omdbapi.com";

    @GET("/")
    Call<Search> buscarFilme(@Query("s") String filme);

    @GET("/")
    Call<Search> buscarFilmeTipo(@Query("s") String filme, @Query("type") String type);

    @GET("/")
    Call<Filme> carregarFilme(@Query("i") String id);

    @GET("/")
    Call<Filme> listarFilmeAleatorio(@Query("i") String id, @Query("type") String type);
}
