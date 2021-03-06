package br.fiap.android.trabalhofinal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

import br.fiap.android.trabalhofinal.adapter.SeriadoAdapter;
import br.fiap.android.trabalhofinal.dao.SeriadoDAO;
import br.fiap.android.trabalhofinal.dao.UsuarioDAO;
import br.fiap.android.trabalhofinal.model.Seriado;
import br.fiap.android.trabalhofinal.model.Usuario;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private final String KEY_APP_PREFERENCES = "trabfinal";
    private final String KEY_LOGIN = "login";
    private RecyclerView rvLista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        rvLista = (RecyclerView) findViewById(R.id.rvLista);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();

                startActivityForResult(new Intent(MainActivity.this, NovoSeriadoActivity.class),1002);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        carregaSeriados();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_sobre) {
            startActivityForResult(new Intent(this, SobreActivity.class),1002);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_novo) {
            startActivityForResult(new Intent(MainActivity.this, NovoSeriadoActivity.class),1002);
        } else if (id == R.id.nav_logoff) {
            desconectar();
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //Chamado qndo retornar para essa tela da tela de cadastro de um novo torcedor
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_CANCELED) {
//            Toast.makeText(MainActivity.this, R.string.cancelado, Toast.LENGTH_LONG).show();
        } else if(requestCode == 1002) {
            carregaSeriados();
        }
    }

    private void carregaSeriados() {
        SeriadoDAO seriadoDAO = new SeriadoDAO(this);
        List<Seriado> lista = seriadoDAO.getAll();
        rvLista.setAdapter(new SeriadoAdapter(lista, this));
        RecyclerView.LayoutManager layout = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvLista.setLayoutManager(layout);
    }
    private void desconectar(){
        SharedPreferences pref = getSharedPreferences(KEY_APP_PREFERENCES, MODE_PRIVATE);
        String login = pref.getString(KEY_LOGIN,"");
        SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        editor.commit();

        if (login!="") {
            UsuarioDAO usuarioDAO = new UsuarioDAO(this);
            Usuario usuario = usuarioDAO.getByLogin(login);
            usuario.setConectado(false);
            usuarioDAO.save(usuario);
        }

    }
}
