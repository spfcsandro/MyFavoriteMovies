package br.com.ilhasoft.myfavoritemovies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bluejamesbond.text.DocumentView;

import br.com.ilhasoft.myfavoritemovies.dao.FilmeDAO;
import br.com.ilhasoft.myfavoritemovies.models.Filme;


public class DetalheFilmeActivity extends AppCompatActivity {

    private ListView lista;
    private TextView tituloDetalhe;
    private TextView generoDetalhe;
    private TextView idiomaDetalhe;
    private TextView anoDetalhe;
    private TextView duracaoDetalhe;
    private TextView direcaoDetalhe;
    private TextView atoresDetalhe;
    private DocumentView sinopseDetalhe;
    private ImageView ivDetalhe;
    private Filme filme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_scrolling);

        tituloDetalhe = (TextView) findViewById(R.id.txtTituloDetalhe);
        generoDetalhe = (TextView) findViewById(R.id.txtGeneroDetalhe);
        idiomaDetalhe = (TextView) findViewById(R.id.txtIdiomaDetalhe);
        anoDetalhe = (TextView) findViewById(R.id.txtAnoDetalhe);
        duracaoDetalhe = (TextView) findViewById(R.id.txtDuracaoDetalhe);
        direcaoDetalhe = (TextView) findViewById(R.id.txtDirecaoDetalhe);
        atoresDetalhe = (TextView) findViewById(R.id.txtAtoresDetalhe);
        sinopseDetalhe = (DocumentView) findViewById(R.id.txtSinopseDetalhe);
        ivDetalhe = (ImageView) findViewById(R.id.ivDetalhe);

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

    }

}
