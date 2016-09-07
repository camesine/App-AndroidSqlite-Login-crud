package ft.app.hector.com.aplicacion1.OpenHelper;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.SQLException;

import ft.app.hector.com.aplicacion1.MainActivity;
import ft.app.hector.com.aplicacion1.Usuario;
import ft.app.hector.com.aplicacion1.UsuarioAdapter;

public class Sqlite_OpenHelper extends SQLiteOpenHelper {

    public Sqlite_OpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE usuarios (_ID integer primary key autoincrement, Nombre TEXT, Correo TEXT, Ciudad text, Pass TEXT); ";
        db.execSQL(query);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }


    public void open(){
        this.getWritableDatabase();
    }


    public void cerrar(){

            this.close();

    }


    public void Nuevo(String nombre, String correo, String ciudad, String pass){
        ContentValues values = new ContentValues();
        values.put("nombre", nombre);
        values.put("correo", correo);
        values.put("ciudad", ciudad);
        values.put("pass", pass);

        this.getWritableDatabase().insert("usuarios", null, values);

    }


    public Cursor Validar(String nombre, String pass) throws SQLException{
        Cursor cursor = null;

        cursor = this.getReadableDatabase().query("usuarios", new String[]{"_ID","Nombre","Correo","Ciudad","Pass"},"Correo like '"+nombre+"' and Pass like '"+pass+"' ",null,null,null,null);
        return cursor;

    }

    public Cursor Listar() {
        Cursor cursor = null;
        cursor = this.getReadableDatabase().query("usuarios", new String[]{"_ID","Nombre","Correo","Ciudad","Pass"},null,null,null,null,null);

        return cursor;
    }

    public Cursor BuscarNombre(String nombre){
        Cursor cursor = null;

        cursor = this.getReadableDatabase().query("usuarios", new String[]{"_ID","Nombre","Correo","Ciudad","Pass"},"Nombre like '"+nombre+"%' ",null,null,null,null);
        return cursor;
    }

    public Cursor BuscarId(int id){
        Cursor cursor = null;

        cursor = this.getReadableDatabase().query("usuarios", new String[]{"_ID","Nombre","Correo","Ciudad","Pass"},"_ID like '"+id+"' ",null,null,null,null);
        return cursor;
    }


    public void BorrarId(int id) {

        this.getWritableDatabase().delete("usuarios", "_ID=" + id, null);
    }

    public void Editar(int id, String nombre, String correo, String ciudad, String pass) {
        ContentValues values = new ContentValues();
        values.put("nombre", nombre);
        values.put("correo", correo);
        values.put("ciudad", ciudad);
        values.put("pass", pass);
        values.put("_ID", id);

        this.getWritableDatabase().update("usuarios", values,"_ID=" + id, null);
    }


    public void BorrarTodo() {
        this.getWritableDatabase().delete("usuarios", null, null);
    }



}
