package com.example.tpnote;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import java.util.List;

public class MainActivity extends AppCompatActivity implements PostExecuteActivity<Pokemon> {
    private final String TAG = "frallo "+getClass().getSimpleName();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.available_languages, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        findViewById(R.id.go).setOnClickListener( clic -> {
            String url = "https://raw.githubusercontent.com/fanzeyi/pokemon.json/17d33dc111ffcc12b016d6485152aa3b1939c214/pokedex.json";
            new HttpAsyncGet<>(url, Pokemon.class, this, new ProgressDialog(clic.getContext()) );
        });

    }




    @Override
    public void onPostExecutePokemons(List<Pokemon> itemList) {
        Pokemon pokemonFirst = itemList.get(0);
        Log.d(TAG,"First pokemon = " + pokemonFirst.getName());
    }
}