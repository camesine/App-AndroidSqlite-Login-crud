package ft.app.hector.com.aplicacion1;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Hector on 05-09-2016.
 */
public class UsuarioAdapter extends ArrayAdapter<Usuario> {

    Context context;
    int layoutresourceid;
    ArrayList<Usuario> data;

    public UsuarioAdapter(Context context, int layoutresourceid, ArrayList<Usuario> data){
        super(context, layoutresourceid, data);

        this.context = context;
        this.layoutresourceid = layoutresourceid;
        this.data = data;

    }

    public View getView(int position, View convertView, ViewGroup parent){

        View row = convertView;
        UsuarioHolder holder = null;

        if(row == null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutresourceid,parent,false);

            holder = new UsuarioHolder();
            holder.id = (TextView)row.findViewById(R.id.Lblid);
            holder.nombre = (TextView)row.findViewById(R.id.LblNombre);
            holder.correo = (TextView)row.findViewById(R.id.LblCorreo);
            holder.ciudad = (TextView)row.findViewById(R.id.LblCiudad);

            Usuario usuario = this.data.get(position);
            holder.id.setText(String.valueOf(usuario.id));
            holder.nombre.setText(usuario.nombre);
            holder.correo.setText(usuario.correo);
            holder.ciudad.setText(usuario.ciudad);


            row.setTag(holder);

        }else{
            holder = (UsuarioHolder)row.getTag();
        }


        return row;

    }


    static class UsuarioHolder{
        TextView id;
        TextView nombre;
        TextView correo;
        TextView ciudad;
        TextView pass;
    }


}
