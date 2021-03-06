package br.fiap.android.trabalhofinal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import br.fiap.android.trabalhofinal.dao.UsuarioDAO;
import br.fiap.android.trabalhofinal.model.Usuario;

public class LoginActivity extends AppCompatActivity {

    private final String KEY_APP_PREFERENCES = "trabfinal";
    private final String KEY_LOGIN = "login";
    private TextInputLayout tilLogin;
    private TextInputLayout tilSenha;
    private CheckBox cbManterConectado;
    private UsuarioDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        dao = new UsuarioDAO(this);
        tilLogin = (TextInputLayout) findViewById(R.id.tilLogin);
        tilSenha = (TextInputLayout) findViewById(R.id.tilSenha);
        cbManterConectado = (CheckBox) findViewById(R.id.cbManterConectado);
        if(isConectado()) {
            iniciarApp();
        }
    }

    //Método que será chamado no onclick do botao
    public void logar(View v){
        if(isLoginValido()){
            if(cbManterConectado.isChecked()){
                manterConectado();
            }
            iniciarApp();
        }else{
            Toast.makeText(this, R.string.usuario_invalido, Toast.LENGTH_SHORT).show();
        }
    }
    // Valida o login
    private boolean isLoginValido() {
        String login = tilLogin.getEditText().getText().toString();
        String senha = tilSenha.getEditText().getText().toString();

        Usuario usuario = dao.getByLogin(login);

        if(usuario!=null && login.equals(usuario.getLogin()) && senha.equals(usuario.getSenha())) {
            return true;
        } else
            return false;
    }
    private void manterConectado(){
        String login = tilLogin.getEditText().getText().toString();

        Usuario usuario = dao.getByLogin(login);
        usuario.setConectado(true);
        dao.save(usuario);

        SharedPreferences pref = getSharedPreferences(KEY_APP_PREFERENCES, MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(KEY_LOGIN, login);
        editor.apply();
    }
    private boolean isConectado() {
        SharedPreferences shared = getSharedPreferences(KEY_APP_PREFERENCES,MODE_PRIVATE);
        String login = shared.getString(KEY_LOGIN, "");
        if(login.equals(""))
            return false;
        else
            return true;
    }
    private void iniciarApp() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
