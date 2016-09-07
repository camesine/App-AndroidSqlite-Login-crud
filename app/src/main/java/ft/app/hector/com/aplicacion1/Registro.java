package ft.app.hector.com.aplicacion1;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import ft.app.hector.com.aplicacion1.OpenHelper.Sqlite_OpenHelper;

public class Registro extends AppCompatActivity implements View.OnClickListener{

     EditText nombre, correo, ciudad, pass;
     Button guardar;

    Sqlite_OpenHelper helper = new Sqlite_OpenHelper(this, "db1", null, 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        nombre = (EditText) findViewById(R.id.TxtNombre);
        correo = (EditText) findViewById(R.id.TxtCorreo);
        ciudad = (EditText) findViewById(R.id.TxtCiudad);
        pass = (EditText) findViewById(R.id.TxtPass);

        guardar = (Button) findViewById(R.id.BtnRegistrar);
        guardar.setOnClickListener(this);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.BtnRegistrar :

                helper.open();

                helper.Nuevo(String.valueOf(this.nombre.getText()),
                        String.valueOf(this.correo.getText()),
                        String.valueOf(this.ciudad.getText()),
                        String.valueOf(this.pass.getText()));
                    helper.cerrar();

                Toast.makeText(getApplicationContext(), "Guardando...", Toast.LENGTH_SHORT).show();

                 Intent i = new Intent(Registro.this , MainActivity.class);

                startActivity(i);
                finish();
        }
    }
}
