package br.com.ilhasoft.myfavoritemovies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;

import br.com.ilhasoft.myfavoritemovies.dao.FilmeDAO;
import br.com.ilhasoft.myfavoritemovies.models.Filme;


public class ListarFilmeActivity extends AppCompatActivity {

    private RecyclerView mRecyclerViewDetalhe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_filme);
        Toolbar mListarToolbar = (Toolbar) findViewById(R.id.listar_toolbar);
        setSupportActionBar(mListarToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        mListarToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mRecyclerViewDetalhe = (RecyclerView) findViewById(R.id.reclyclerViewDetalhe);
        listarFilmes();

    }

    public void listarFilmes() {

        FilmeDAO filmeDAO = new FilmeDAO(this);

        ArrayList<Filme> filmes = (ArrayList<Filme>) filmeDAO.listar();
        GridLayoutManager mGridLayoutManager = new GridLayoutManager(this,2);
        mRecyclerViewDetalhe.setLayoutManager(mGridLayoutManager);
        mRecyclerViewDetalhe.setAdapter(new ListarFilmeAdapter(this, filmes));
    }

}
