package curso.umg.gt.umgappproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Registro extends AppCompatActivity {
    EditText nombres,apellidos,edad,username,password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        nombres = (EditText) findViewById(R.id.etNombres);
        apellidos = (EditText) findViewById(R.id.etApellidos);
        edad = (EditText) findViewById(R.id.etEdad);
        username = (EditText) findViewById(R.id.etUserName);
        password = (EditText) findViewById(R.id.etPassword);

    }
    public void onRegistro(View view){
        String str_nombre = nombres.getText().toString();
        String str_apellidos = apellidos.getText().toString();
        String str_edad = edad.getText().toString();
        String str_username = username.getText().toString();
        String str_password = password.getText().toString();
        String tipoOperacion = "registro";
        ConexionLogin conexionLogin = new ConexionLogin(this);
        conexionLogin.execute(tipoOperacion, str_nombre, str_apellidos,str_edad,str_username,str_password);

        nombres.setText("");
        apellidos.setText("");
        edad.setText("");
        username.setText("");
        password.setText("");

    }
}
