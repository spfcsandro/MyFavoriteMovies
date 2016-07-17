package br.com.ilhasoft.myfavoritemovies.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by SandroAndrade on 04/07/2016.
 */

public class Filme implements Serializable {

    private static final long serialVersionUID = 1L;
    private long id;
    @SerializedName("imdbID")
    private String imdbID;
    @SerializedName("Title")
    private String titulo;
    @SerializedName("Year")
    private String ano;
    @SerializedName("Runtime")
    private String tempo;
    @SerializedName("Genre")
    private String genero;
    @SerializedName("Director")
    private String direcao;
    @SerializedName("Actors")
    private String elenco;
    @SerializedName("Plot")
    private String sinopse;
    @SerializedName("Language")
    private String idioma;
    @SerializedName("Country")
    private String nacionalidade;
    @SerializedName("Poster")
    private String imagem;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getTempo() {
        return tempo;
    }

    public void setTempo(String tempo) {
        this.tempo = tempo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getDirecao() {
        return direcao;
    }

    public void setDirecao(String direcao) {
        this.direcao = direcao;
    }

    public String getElenco() {
        return elenco;
    }

    public void setElenco(String elenco) {
        this.elenco = elenco;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }


}
