package ft.app.hector.com.aplicacion1;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Hector on 06-09-2016.
 */
public class Sesion {
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    Context context;


    public Sesion (Context context){
        this.context = context;
        this.prefs = context.getSharedPreferences("app",context.MODE_PRIVATE);
        editor = prefs.edit();

    }

    public void setLogin(Boolean login){
        editor.putBoolean("LoginMode",login);
        editor.commit();
    }

    public boolean getLogin(){
        return prefs.getBoolean("LoginMode", false);
    }

}
