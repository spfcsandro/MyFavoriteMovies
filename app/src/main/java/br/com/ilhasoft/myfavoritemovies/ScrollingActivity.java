package br.com.ilhasoft.myfavoritemovies;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bluejamesbond.text.DocumentView;
import com.squareup.picasso.Picasso;

import br.com.ilhasoft.myfavoritemovies.dao.FilmeDAO;
import br.com.ilhasoft.myfavoritemovies.models.Filme;

public class ScrollingActivity extends AppCompatActivity {

    TextView tituloDetalhe;
    TextView generoDetalhe;
    TextView idiomaDetalhe;
    TextView anoDetalhe;
    TextView duracaoDetalhe;
    TextView direcaoDetalhe;
    TextView atoresDetalhe;
    DocumentView sinopseDetalhe;
    ImageView ivDetalhe;
    CollapsingToolbarLayout toolbar_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        tituloDetalhe = (TextView) findViewById(R.id.txtTituloDetalhe);
        generoDetalhe = (TextView) findViewById(R.id.txtGeneroDetalhe);
        idiomaDetalhe = (TextView) findViewById(R.id.txtIdiomaDetalhe);
        anoDetalhe = (TextView) findViewById(R.id.txtAnoDetalhe);
        duracaoDetalhe = (TextView) findViewById(R.id.txtDuracaoDetalhe);
        direcaoDetalhe = (TextView) findViewById(R.id.txtDirecaoDetalhe);
        atoresDetalhe = (TextView) findViewById(R.id.txtAtoresDetalhe);
        sinopseDetalhe = (DocumentView) findViewById(R.id.txtSinopseDetalhe);
        ivDetalhe = (ImageView) findViewById(R.id.ivDetalhe);
        toolbar_layout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);

        detalhe();
    }


    public void detalhe() {
        FilmeDAO filmeDAO = new FilmeDAO(this);
        Filme filmeParam = (Filme) getIntent().getSerializableExtra("FILME_SELECIONADO");
        Filme filme = filmeDAO.carregaFilmePorIMDBID(filmeParam.getImdbID());
        setarFilmeNaActivity(filme);


    }

    public void setarFilmeNaActivity(Filme filme){

        tituloDetalhe.setText(filme.getTitulo());
        generoDetalhe.setText(filme.getGenero());
        idiomaDetalhe.setText(filme.getIdioma());
        anoDetalhe.setText(filme.getAno());
        duracaoDetalhe.setText(filme.getTempo());
        direcaoDetalhe.setText(filme.getDirecao());
        atoresDetalhe.setText(filme.getElenco());
        sinopseDetalhe.setText(filme.getSinopse());

        toolbar_layout.setTitle(filme.getTitulo());



        if(!TextUtils.equals(filme.getImagem(),"N/A")){
            Picasso.with(this)
                    .load(filme.getImagem())
                    .error(R.drawable.android_verde)
                    .resize(300, 450)
                    .into(ivDetalhe);
        }else{
            Picasso.with(this)
                    .load(R.drawable.android_verde)
                    .resize(300, 450)
                    .into(ivDetalhe);
        }

    }

}
