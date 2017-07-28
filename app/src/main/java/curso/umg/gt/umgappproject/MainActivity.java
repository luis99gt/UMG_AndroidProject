package curso.umg.gt.umgappproject;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private EditText userName, pass;
    private TextInputLayout impU, impP;
    boolean v_user=false, v_pass=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userName = (EditText) findViewById(R.id.etUserName);
        pass = (EditText) findViewById(R.id.etPassword);

        impU = (TextInputLayout) findViewById(R.id.impUsername);
        impP = (TextInputLayout) findViewById(R.id.impPassword);

    }

    public void login(View view){
        String username = userName.getText().toString();
        String password = pass.getText().toString();
        String tipoOperacion = "login";

        //Validación
        if (Patterns.EMAIL_ADDRESS.matcher(username).matches()== false){
            impU.setError("Usuario Inválido");
            v_user = false;
        }else {
            v_user = true;
            impU.setError(null);
        }

        //Crear Patron
//        Pattern p = Pattern.compile("[0-9][0-9][0-9][0-9][0-9]");
//        if (p.matcher(password).matches() == false){
//            impP.setError("Contraseña válida de 5 números");
//            v_pass=false;
//        }else{
//            v_pass=true;
//            impP.setError(null);
//        }

        if (pass.getText().length()>= 8){
            impP.setError(null);
            v_pass = true;
        }else {
            impP.setError("Debe contener mas de 8 caracteres");
            v_pass = false;
        }



        if (v_user == true && v_pass == true){
            ConexionLogin conexionLogin = new ConexionLogin(this);
            conexionLogin.execute(tipoOperacion, username, password);
        }



    }

    public void openRegistro(View view) {
        startActivity(new Intent(this,Registro.class));
    }
    public void openLista(View view) {
        startActivity(new Intent(this,ListaUsuarios.class));
    }
}
