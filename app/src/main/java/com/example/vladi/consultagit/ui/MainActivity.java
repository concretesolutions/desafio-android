package com.example.vladi.consultagit.ui;
import com.example.vladi.consultagit.R;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner technologies = (Spinner) findViewById(R.id.technologies);
        Context context = this;
        SharedPreferences sharedPreferences = getSharedPreferences("Queries",this.MODE_PRIVATE);

        ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.technologies));
        mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        technologies.setAdapter(mAdapter);
        technologies.setOnItemSelectedListener(this);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(position != 0){
            Intent intent = new Intent(MainActivity.this, RepositoriesActivity.class);
            intent.putExtra("Query", parent.getItemAtPosition(position).toString());
            startActivity(intent);
            parent.setSelection(0);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

}
