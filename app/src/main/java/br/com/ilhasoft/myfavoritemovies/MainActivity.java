package br.com.ilhasoft.myfavoritemovies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void acessarBuscarFilmeActivity(View v) {
        Intent intent = new Intent(this, BuscarFilmeActivity.class);
        startActivity(intent);
    }

    public void acessarListarFilmeActivity(View v) {
        Intent intent = new Intent(this, ListarFilmeActivity.class);
        startActivity(intent);
    }
}
