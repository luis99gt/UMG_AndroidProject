package curso.umg.gt.umgappproject;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ListaUsuarios extends AppCompatActivity {

    ListView lv;
    ArrayAdapter<String> adapter;
    String listU = "http://umgandroid.txolja.com/listUsers.php";
    InputStream is = null;
    String line = null;
    String result =null;
    String[] data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_usuarios);
        lv = (ListView) findViewById(R.id.lvUser);

        //Allow network in main thread
        StrictMode.setThreadPolicy((new StrictMode.ThreadPolicy.Builder().permitNetwork().build()));
        //Retrive
        getDataUser();

        //adapter
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,data);
        lv.setAdapter(adapter);

    }

    private void getDataUser(){
        try {
            URL url = new URL(listU);
            HttpURLConnection con=(HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            is=new BufferedInputStream(con.getInputStream());



        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Leer el contenido
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();

            while ((line =br.readLine())!= null ){
                sb.append(line+"\n");
            }
            is.close();
            result = sb.toString();
        }catch (Exception e){
            e.printStackTrace();
        }

        //Parse Json Data
        try{
            JSONArray ja = new JSONArray(result);
            JSONObject jo = null;
            data = new String[ja.length()];

            for (int i=0; i<ja.length();i++){
                jo=ja.getJSONObject(i);
                String name = jo.getString("nombres");
                data[i]= "Id: "+jo.getString("id")
                        +"\nNombres: "+name
                        +"\nApellidos: "+jo.getString("apellidos")
                        +"\nFecha de Nacimiento: "+jo.getString("edad")
                        +"\nUsername: "+jo.getString("username")
                        +"\nPassword: **** ";

            }
        }catch (Exception e){
            e.printStackTrace();
        }



    }
}
