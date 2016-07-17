package br.com.ilhasoft.myfavoritemovies;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import br.com.ilhasoft.myfavoritemovies.models.Search;
import br.com.ilhasoft.myfavoritemovies.service.OMDBAPIService;
import br.com.ilhasoft.myfavoritemovies.util.TecladoUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class BuscarFilmeActivity extends AppCompatActivity {

    private SearchView mSearchView;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_filme);

        Toolbar mBuscarToolbar = (Toolbar) findViewById(R.id.buscar_toolbar);
        setSupportActionBar(mBuscarToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        mBuscarToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mSearchView = (SearchView) findViewById(R.id.svBuscarFilme);
        mSearchView.setIconified(false);
        mSearchView.setFocusable(true);

        mRecyclerView = (RecyclerView) findViewById(R.id.reclyclerView);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {

                if (verificaConexao()) {

                    String filtro = mSearchView.getQuery().toString();

                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(OMDBAPIService.BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    OMDBAPIService service = retrofit.create(OMDBAPIService.class);
                    Call<Search> requestListaFilmes = service.buscarFilme(filtro);


                    requestListaFilmes.enqueue(new Callback<Search>() {
                        @Override
                        public void onResponse(Call<Search> call, Response<Search> response) {
                            if (response.isSuccessful()) {
                                Search filmes = response.body();
                                if (filmes.getSearch() != null) {
                                    mRecyclerView.setAdapter(new FilmeAdapter(getBaseContext(), filmes.getSearch()));
                                    TecladoUtil.hideKeyboard(getBaseContext(), mSearchView);
                                } else {
                                    Toast.makeText(getApplicationContext(), "Filme não encontrado!!!", Toast.LENGTH_SHORT).show();
                                }

                            } else {

                                Toast.makeText(getApplicationContext(), "Ocorreu algum problema, por favor contactar o administrador.", Toast.LENGTH_SHORT).show();
                                Log.e("RESPONSE", "Erro:" + response.code());
                            }
                        }

                        @Override
                        public void onFailure(Call<Search> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), "Ocorreu algum problema, por favor contactar o administrador.", Toast.LENGTH_SHORT).show();
                             Log.e("RETORNO", "Erro:" + t.getMessage());
                        }
                    });

                } else {
                    Toast.makeText(getApplicationContext(), "Sem conexão", Toast.LENGTH_SHORT).show();
                }

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

    }

    public boolean verificaConexao() {

        ConnectivityManager cm = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}
