package br.com.ilhasoft.myfavoritemovies.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by SandroAndrade on 04/07/2016.
 */
public class Search {

    @SerializedName(value="Search")
    private ArrayList<Filme> search;


    public ArrayList<Filme> getSearch() {
        return search;
    }

    public void setSearch(ArrayList<Filme> search) {
        this.search = search;
    }
}
