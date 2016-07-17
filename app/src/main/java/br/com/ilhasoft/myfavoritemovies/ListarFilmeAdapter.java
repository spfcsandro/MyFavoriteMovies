package br.com.ilhasoft.myfavoritemovies;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import br.com.ilhasoft.myfavoritemovies.dao.FilmeDAO;
import br.com.ilhasoft.myfavoritemovies.models.Filme;

/**
 * Created by SandroAndrade on 04/07/2016.
 */
public class ListarFilmeAdapter extends RecyclerView.Adapter<ListarFilmeAdapter.FilmeViewHolder> {

    Context context;
    ArrayList<Filme> lista;
    private AlertDialog alerta;

    public ListarFilmeAdapter(Context context, ArrayList<Filme> lista){
        this.context = context;
        this.lista = lista;
    }


    @Override
    public FilmeViewHolder onCreateViewHolder(ViewGroup parent, int position) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.galeria_filmes, parent, false);

        FilmeViewHolder holder = new FilmeViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(FilmeViewHolder holder, final int position) {

        Filme filme = lista.get(position);

        if(!TextUtils.equals(filme.getImagem(),"N/A")){
            Picasso.with(context)
                    .load(filme.getImagem())
                    .error(R.drawable.android_verde)
                    .into(holder.imagemFilme);
        }else{
            Picasso.with(context)
                    .load(R.drawable.android_verde)
                    .into(holder.imagemFilme);
        }

        holder.tituloFilme.setText(filme.getTitulo());
        holder.anoFilme.setText(filme.getAno());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, ScrollingActivity.class);
                Filme filme = (Filme) lista.get(position);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("FILME_SELECIONADO", filme);
                context.startActivity(intent);
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.AppTheme_AppBarOverlay);

                builder.setNeutralButton("Remover dos Favoritos", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        FilmeDAO filmeDAO = new FilmeDAO(context);
                        filmeDAO.deletar(lista.get(position).getImdbID());
                        lista = (ArrayList<Filme>) filmeDAO.listar();
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position,lista.size());
                    }
                });

                alerta =  builder.create();
                alerta.getWindow().setLayout(450, 100);
                alerta.show();

                return true;
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



        public FilmeViewHolder(View itemView) {
            super(itemView);

            tituloFilme = (TextView) itemView.findViewById(R.id.txtTituloOMDB);
            anoFilme = (TextView) itemView.findViewById(R.id.txtAnoOMDB);
            imagemFilme = (ImageView) itemView.findViewById(R.id.ivImageOMDB);

        }
    }
}
