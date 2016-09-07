package ft.app.hector.com.aplicacion1;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;

import ft.app.hector.com.aplicacion1.OpenHelper.Sqlite_OpenHelper;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

        TextView registrar;
        Sqlite_OpenHelper helper = new Sqlite_OpenHelper(this, "db1", null, 1);
        Button entrar;
        private TextView usuario, pass;
        Sesion sesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sesion = new Sesion(this);

        if(sesion.getLogin()){
            Intent in = new Intent(getApplicationContext(), INICIO.class);
            startActivity(in);
            finish();
        }


        usuario = (EditText) findViewById(R.id.TxtCorreo);
        pass = (EditText) findViewById(R.id.TxtPass);

        registrar = (TextView) findViewById(R.id.TxtRegistrarse);
        registrar.setOnClickListener(this);

        entrar = (Button) findViewById(R.id.BtnEntrar);
        entrar.setOnClickListener(this);

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

        switch (v.getId()){
            case R.id.TxtRegistrarse :
                Intent i = new Intent(getApplicationContext(), Registro.class);
                startActivity(i);

                break;

            case R.id.BtnEntrar :
                helper.open();

                try {
                    Cursor cursor = helper.Validar(String.valueOf(this.usuario.getText()), String.valueOf(this.pass.getText()));
                    if(cursor.getCount()==1){
                        sesion.setLogin(true);
                        Intent in = new Intent(getApplicationContext(), INICIO.class);
                        startActivity(in);
                        finish();

                    }else{
                        Toast.makeText(getApplicationContext(), "DATOS INCORRECTOS", Toast.LENGTH_SHORT).show();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }




        }

    }
}
