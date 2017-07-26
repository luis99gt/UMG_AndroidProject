package curso.umg.gt.umgappproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText userName, pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userName = (EditText) findViewById(R.id.etUserName);
        pass = (EditText) findViewById(R.id.etPassword);

    }

    public void login(View view){
        String username = userName.getText().toString();
        String password = pass.getText().toString();
        String tipoOperacion = "login";
        ConexionLogin conexionLogin = new ConexionLogin(this);
        conexionLogin.execute(tipoOperacion, username, password);

    }

    public void openRegistro(View view) {
        startActivity(new Intent(this,Registro.class));
    }
}
