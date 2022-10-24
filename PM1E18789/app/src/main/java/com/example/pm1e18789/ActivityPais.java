package com.example.pm1e18789;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pm1e18789.configuraciones.SQLiteConexion;
import com.example.pm1e18789.configuraciones.Transacciones;

public class ActivityPais extends AppCompatActivity {

    EditText codigo_pais,nombre_pais;
    Button guardar_pais, atras_pais;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pais);

        codigo_pais = (EditText) findViewById(R.id.txt_codigo_pais);
        nombre_pais = (EditText) findViewById(R.id.txt_nombre_pais);
        guardar_pais = (Button) findViewById(R.id.btn_guardar_pais);
        atras_pais = (Button) findViewById(R.id.btn_atras_pais);

        guardar_pais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InsertarPais();
            }
        });

        atras_pais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void InsertarPais() {
        SQLiteConexion conexion = new SQLiteConexion(this, Transacciones.NameDatabase,null,1);
        SQLiteDatabase db = conexion.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put(Transacciones.codigo,codigo_pais.getText().toString());
        valores.put(Transacciones.p_pais,nombre_pais.getText().toString());

        Long resultado = db.insert(Transacciones.tblPaises,Transacciones.codigo,valores);

        Toast.makeText(getApplicationContext(),"Registro Exitoso!!!"+resultado.toString(),Toast.LENGTH_LONG).show();
        db.close();

        limpiarPantalla();

    }

    private void limpiarPantalla() {
        codigo_pais.setText("");
        nombre_pais.setText("");
    }
}