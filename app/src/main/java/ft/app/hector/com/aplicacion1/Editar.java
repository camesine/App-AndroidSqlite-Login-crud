package ft.app.hector.com.aplicacion1;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ft.app.hector.com.aplicacion1.OpenHelper.Sqlite_OpenHelper;

public class Editar extends AppCompatActivity {

    EditText NombreEditar;
    EditText CorreoEditar;
    EditText CiudadEditar;
    EditText PassEditar;
    Button BtnEditar;
    Sqlite_OpenHelper helper = new Sqlite_OpenHelper(this,"db1",null,1);
    String idSeleccion;
    Sesion sesion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        sesion = new Sesion(this);

        if(!sesion.getLogin()){
            sesion.setLogin(false);
            Intent i = new Intent(Editar.this,MainActivity.class);
            startActivity(i);
            finish();
        }


        NombreEditar = (EditText) findViewById(R.id.TxtNombreEditar);
        CorreoEditar = (EditText) findViewById(R.id.TxtCorreoEditar);
        CiudadEditar = (EditText) findViewById(R.id.TxtCiudadEditar);
        PassEditar = (EditText) findViewById(R.id.TxtPassEditar);
        BtnEditar = (Button) findViewById(R.id.BtnEditar);

        idSeleccion = getIntent().getExtras().getString("dato");

        Cursor c = helper.BuscarId(Integer.parseInt(idSeleccion));


        if (c.moveToFirst()) {
            do {
                NombreEditar.setText(c.getString(1));
                CorreoEditar.setText(c.getString(2));
                CiudadEditar.setText(c.getString(3));
               // PassEditar.setText(c.getString(4));
            } while(c.moveToNext());
        }


        BtnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                helper.Editar(Integer.parseInt(idSeleccion),String.valueOf(NombreEditar.getText()),String.valueOf(CorreoEditar.getText()),String.valueOf(CiudadEditar.getText()),String.valueOf(PassEditar.getText()));

                Intent i = new Intent(Editar.this,INICIO.class);
                startActivity(i);
                finish();


            }
        });



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
