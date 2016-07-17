package br.com.ilhasoft.myfavoritemovies.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.ilhasoft.myfavoritemovies.models.Filme;

public class FilmeDAO {
	private SQLiteDatabase bd;

	public FilmeDAO(Context context){
		BDCore auxBd = new BDCore(context);
		bd = auxBd.getWritableDatabase();
	}


	public void salvar(Filme filme){
		ContentValues valores = new ContentValues();
		valores.put("imdbID", filme.getImdbID());
		valores.put("titulo", filme.getTitulo());
		valores.put("ano", filme.getAno());
		valores.put("tempo", filme.getTempo());
		valores.put("genero", filme.getGenero());
		valores.put("direcao", filme.getDirecao());
		valores.put("elenco", filme.getElenco());
		valores.put("sinopse", filme.getSinopse());
		valores.put("idioma", filme.getIdioma());
		valores.put("nacionalidade", filme.getNacionalidade());
		valores.put("imagem", filme.getImagem());

		bd.insert("filme", null, valores);
	}



	/*public void deletar(Filme filme){
		bd.delete("filme", "imdbID = ? "+filme.getImdbID(), null);
	}*/

	public void deletar(String imdbID){
		bd.delete("filme", "imdbID = "+"'"+imdbID+"'", null);
	}


	public List<Filme> listar(){
		List<Filme> listaFilme = new ArrayList<Filme>();
		String[] colunas = new String[]{"_id",
										"imdbID",
										"titulo",
										"ano",
										"tempo",
										"genero",
										"direcao",
									    "elenco",
										"sinopse",
										"idioma",
										"nacionalidade",
										"imagem"};

		Cursor cursor = bd.query("filme", colunas, null, null, null, null, "titulo ASC");

		if(cursor.getCount() > 0){
			cursor.moveToFirst();

			do{

				Filme filme = new Filme();
				filme.setId(cursor.getLong(0));
				filme.setImdbID(cursor.getString(1));
				filme.setTitulo(cursor.getString(2));
				filme.setAno(cursor.getString(3));
				filme.setTempo(cursor.getString(4));
				filme.setGenero(cursor.getString(5));
				filme.setDirecao(cursor.getString(6));
				filme.setElenco(cursor.getString(7));
				filme.setSinopse(cursor.getString(8));
				filme.setIdioma(cursor.getString(9));
				filme.setNacionalidade(cursor.getString(10));
				filme.setImagem(cursor.getString(11));
				listaFilme.add(filme);

			}while(cursor.moveToNext());
		}

		return(listaFilme);
	}

	public boolean existeFilme (String imdbId) {
		String[] colunas = new String[]{"_id",
				"imdbID",
				"titulo",
				"ano",
				"tempo",
				"genero",
				"direcao",
				"elenco",
				"sinopse",
				"idioma",
				"nacionalidade",
				"imagem"};

		Cursor cursor = bd.query("filme", colunas, "imdbID=?", new String[] { imdbId }, null, null, null);


		return cursor.moveToFirst();

	}

	public Filme carregaFilmePorIMDBID (String imdbId) {
		String[] colunas = new String[]{"_id",
				"imdbID",
				"titulo",
				"ano",
				"tempo",
				"genero",
				"direcao",
				"elenco",
				"sinopse",
				"idioma",
				"nacionalidade",
				"imagem"};

		Cursor cursor = bd.query("filme", colunas, "imdbID=?", new String[] { imdbId }, null, null, null);

		Filme filme = new Filme();

		if(cursor.moveToFirst()) {

			filme.setId(cursor.getLong(0));
			filme.setImdbID(cursor.getString(1));
			filme.setTitulo(cursor.getString(2));
			filme.setAno(cursor.getString(3));
			filme.setTempo(cursor.getString(4));
			filme.setGenero(cursor.getString(5));
			filme.setDirecao(cursor.getString(6));
			filme.setElenco(cursor.getString(7));
			filme.setSinopse(cursor.getString(8));
			filme.setIdioma(cursor.getString(9));
			filme.setNacionalidade(cursor.getString(10));
			filme.setImagem(cursor.getString(11));
		}



		return filme;

	}

}
