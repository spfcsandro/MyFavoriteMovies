package br.com.ilhasoft.myfavoritemovies;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import br.com.ilhasoft.myfavoritemovies.dao.FilmeDAO;
import br.com.ilhasoft.myfavoritemovies.models.Filme;
import br.com.ilhasoft.myfavoritemovies.service.OMDBAPIService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by SandroAndrade on 04/07/2016.
 */
public class FilmeAdapter extends RecyclerView.Adapter<FilmeAdapter.FilmeViewHolder> {

    Context context;
    ArrayList<Filme> lista;

    public FilmeAdapter(Context context, ArrayList<Filme> lista){
        this.context = context;
        this.lista = lista;
    }

    @Override
    public FilmeViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.filmes, parent, false);

        FilmeViewHolder holder = new FilmeViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final FilmeViewHolder holder, final int position) {
        Filme filme = lista.get(position);

        if(!TextUtils.equals(filme.getImagem(),"N/A")){
            Picasso.with(context)
                    .load(filme.getImagem())
                    .resize(340, 490)
                    .into(holder.imagemFilme);
        }else{
            Picasso.with(context)
                    .load(R.drawable.android_verde)
                    .resize(340, 490)
                    .into(holder.imagemFilme);
        }

           holder.tituloFilme.setText(filme.getTitulo());
           holder.anoFilme.setText(filme.getAno());
           existeFavorito(lista.get(position).getImdbID(),holder,position);

        holder.imagemFavorito.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    FilmeDAO filmeDAO = new FilmeDAO(context);
                    Filme filme = (Filme) lista.get(position);

                    if(filmeDAO.existeFilme(lista.get(position).getImdbID())){
                        filmeDAO.deletar(lista.get(position).getImdbID());
                        holder.imagemFavorito.setImageResource(R.drawable.star_outline36);
                    }else{
                        salvarFilme(lista.get(position).getImdbID());
                        existeFavorito(lista.get(position).getImdbID(),holder,position);
                        holder.imagemFavorito.setImageResource(R.drawable.star36);
                    }

                }
            });

    }

    public void existeFavorito(String imdbID, FilmeViewHolder holder, int position){

        FilmeDAO filmeDAO = new FilmeDAO(context);

        if(filmeDAO.existeFilme(lista.get(position).getImdbID())){
            holder.imagemFavorito.setImageResource(R.drawable.star36);
        }else{
            holder.imagemFavorito.setImageResource(R.drawable.star_outline36);
        }
    }

    public void salvarFilme(String imdbID){

        final FilmeDAO filmeDAO = new FilmeDAO(context);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(OMDBAPIService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        OMDBAPIService service = retrofit.create(OMDBAPIService.class);
        Call<Filme> requestListaFilmes = service.carregarFilme(imdbID);


        requestListaFilmes.enqueue(new Callback<Filme>() {
            @Override
            public void onResponse(Call<Filme> call, Response<Filme> response) {
                if(response.isSuccessful()){

                    Filme filmeRequest  = response.body();
                    Filme filme  = new Filme();
                    filme.setSinopse(filmeRequest.getSinopse());
                    filme.setAno(filmeRequest.getAno());
                    filme.setTitulo(filmeRequest.getTitulo());
                    filme.setDirecao(filmeRequest.getDirecao());
                    filme.setElenco(filmeRequest.getElenco());
                    filme.setGenero(filmeRequest.getGenero());
                    filme.setIdioma(filmeRequest.getIdioma());
                    filme.setImagem(filmeRequest.getImagem());
                    filme.setImdbID(filmeRequest.getImdbID());
                    filme.setNacionalidade(filmeRequest.getNacionalidade());
                    filme.setTempo(filmeRequest.getTempo());
                    filmeDAO.salvar(filme);


                }else{
                    Toast.makeText(context, "Ocorreu algum problema, por favor contactar o administrador.", Toast.LENGTH_SHORT).show();
                    Log.e("RESPONSE", "Erro:" + response.code());
                }
            }

            @Override
            public void onFailure(Call<Filme> call, Throwable t) {
                Toast.makeText(context, "Ocorreu algum problema, por favor contactar o administrador.", Toast.LENGTH_SHORT).show();
                Log.e("RETORNO", "Erro:" + t.getMessage());
            }
        });

    }

    @Override
    public int getItemCount() {
        return lista.size();
    }


    class FilmeViewHolder extends RecyclerView.ViewHolder {

        TextView tituloFilme;
        TextView anoFilme;
        ImageView imagemFilme;
        ImageView imagemFavorito;



        public FilmeViewHolder(View itemView) {
            super(itemView);

            tituloFilme = (TextView) itemView.findViewById(R.id.txtTituloOMDB);
            anoFilme = (TextView) itemView.findViewById(R.id.txtAnoOMDB);
            imagemFilme = (ImageView) itemView.findViewById(R.id.ivImageOMDB);
            imagemFavorito = (ImageView) itemView.findViewById(R.id.ivFavorito);

        }
    }

}
