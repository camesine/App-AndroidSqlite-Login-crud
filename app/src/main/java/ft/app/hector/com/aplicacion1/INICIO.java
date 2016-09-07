package ft.app.hector.com.aplicacion1;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ClipData;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import ft.app.hector.com.aplicacion1.OpenHelper.Sqlite_OpenHelper;

public class INICIO extends AppCompatActivity implements View.OnClickListener{

    ListView lista;
    Button buscar;
    Sqlite_OpenHelper helper = new Sqlite_OpenHelper(this,"db1",null,1);
    EditText TxtBuscarUser;
    Button BuscarTodos;
    final ArrayList<Usuario> usuarios = new ArrayList<>();
    Sesion sesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lista = (ListView) findViewById(R.id.LstUsuarios);

        Cursor c = helper.Listar();

        sesion = new Sesion(this);

        if(!sesion.getLogin()){
            sesion.setLogin(false);
            Intent i = new Intent(INICIO.this,MainActivity.class);
            startActivity(i);
            finish();
        }





        if (c.moveToFirst()) {
            do {
                Usuario user = new Usuario(c.getInt(0),c.getString(1),c.getString(2),c.getString(3),c.getString(4));
                usuarios.add(user);
            } while(c.moveToNext());
        }


        final UsuarioAdapter adapter = new UsuarioAdapter(this,R.layout.listview_item_row,usuarios);


        lista = (ListView) findViewById(R.id.LstUsuarios);
        lista.setAdapter(adapter);



        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                AlertDialog.Builder builder = new AlertDialog.Builder(INICIO.this);
                ListView ModalOpciones = new ListView(INICIO.this);

                String[] Opciones = new String[]{"Editar" , "Borrar"};
                ArrayAdapter<String> modeAdapter = new ArrayAdapter<>(INICIO.this, android.R.layout.simple_list_item_1, Opciones);
                ModalOpciones.setAdapter(modeAdapter);

                builder.setView(ModalOpciones);

                final Dialog dialog = builder.create();
                dialog.show();

                String contactId = ((TextView) view.findViewById(R.id.Lblid)).getText().toString();


                final int idSeleccion = Integer.parseInt(contactId);

                ModalOpciones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        if(position == 0){

                            Intent intent = new Intent(INICIO.this,Editar.class);
                            intent.putExtra("dato", String.valueOf(idSeleccion));
                            startActivity(intent);


                        }else{

                            helper.BorrarId(idSeleccion);
                            BuscarTodos.performClick();

                        }
                        dialog.dismiss();
                    }
                });


            }
        });


        buscar = (Button) findViewById(R.id.BtnBuscar);
        buscar.setOnClickListener(this);

        BuscarTodos = (Button) findViewById(R.id.BtnTodos);
        BuscarTodos.setOnClickListener(this);

        TxtBuscarUser = (EditText) findViewById(R.id.TxtNombre);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }


    //MENU
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        if (id == R.id.Salir) {
            sesion.setLogin(false);
            Intent i = new Intent(INICIO.this,MainActivity.class);
            startActivity(i);
            finish();
        }
        if (id == R.id.BorrarTodo) {
            helper.BorrarTodo();
            BuscarTodos.performClick();
        }

        return super.onOptionsItemSelected(item);
    }




    @Override
    public void onClick(View v) {
        UsuarioAdapter adapter = null;
        Cursor c = null;
        ArrayList<Usuario> usuarios = new ArrayList<>();
        switch(v.getId()){
            case R.id.BtnBuscar :
                c = this.helper.BuscarNombre(String.valueOf(TxtBuscarUser.getText()));
                Toast.makeText(getApplicationContext(),"Cantidad : " + c.getCount() , Toast.LENGTH_SHORT).show();



                if (c.moveToFirst()) {
                    do {
                        Usuario user = new Usuario(c.getInt(0),c.getString(1),c.getString(2),c.getString(3),c.getString(4));
                        usuarios.add(user);
                    } while(c.moveToNext());
                }


                 adapter = new UsuarioAdapter(this,R.layout.listview_item_row,usuarios);



                lista.setAdapter(adapter);

                break;



            case R.id.BtnTodos :

                c = this.helper.Listar();
                Toast.makeText(getApplicationContext(),"Cantidad : " + c.getCount() , Toast.LENGTH_SHORT).show();



                if (c.moveToFirst()) {
                    do {
                        Usuario user = new Usuario(c.getInt(0),c.getString(1),c.getString(2),c.getString(3),c.getString(4));
                        usuarios.add(user);
                    } while(c.moveToNext());
                }

                adapter = new UsuarioAdapter(this,R.layout.listview_item_row,usuarios);



                lista.setAdapter(adapter);


                break;

            default:
                Toast.makeText(getApplicationContext(),"DEFAULT", Toast.LENGTH_SHORT).show();
                break;
        }

    }


}
