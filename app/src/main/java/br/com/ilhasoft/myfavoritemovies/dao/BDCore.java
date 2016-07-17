package br.com.ilhasoft.myfavoritemovies.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BDCore extends SQLiteOpenHelper {
	private static final String NOME_BD = "ilhafilmsdb";
	private static final int VERSAO_BD = 3;
	
	
	public BDCore(Context ctx){
		super(ctx, NOME_BD, null, VERSAO_BD);
	}
	
	
	@Override
	public void onCreate(SQLiteDatabase bd) {
		bd.execSQL("create table filme(_id integer primary key autoincrement, " +
				"imdbID text not null, " +
				"titulo text not null, " +
				"ano text not null, " +
				"tempo text not null, " +
				"genero text not null, " +
				"direcao text not null, " +
				"elenco text not null, " +
				"sinopse text not null, " +
				"idioma text not null, " +
				"nacionalidade text not null, " +
				"imagem text not null);");
	}

	@Override
	public void onUpgrade(SQLiteDatabase bd, int arg1, int arg2) {
		bd.execSQL("drop table filme;");
		onCreate(bd);
	}


}
