package br.fiap.android.trabalhofinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import br.fiap.android.trabalhofinal.dao.SeriadoDAO;
import br.fiap.android.trabalhofinal.model.Seriado;

public class NovoSeriadoActivity extends AppCompatActivity {

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_novo_seriado);
//    }
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private EditText etNome;
    private DatePicker dpData;
    private Integer id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_seriado);
        etNome = (EditText) findViewById(R.id.etNome);
        dpData = (DatePicker) findViewById(R.id.dpData);

        carregarSeriado();
    }
    public void cadastrar(View v) {
        SeriadoDAO seriadoDAO = new SeriadoDAO(this);
        Seriado seriado = new Seriado();
        seriado.setNome(etNome.getText().toString());
        try {
            int day = dpData.getDayOfMonth();
            int month = dpData.getMonth();
            int year =  dpData.getYear();

            Calendar calendar = Calendar.getInstance();
            calendar.set(year, month, day);
            seriado.setData(sdf.parse(sdf.format(calendar.getTime())));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (id==null) {
            seriadoDAO.add(seriado);
        }else{
            seriado.setId(id);
            seriadoDAO.update(seriado);
        }
        retornaParaTelaAnterior();
    }
    //retorna para tela de lista de torcedores
    public void retornaParaTelaAnterior() {
        Intent intentMessage=new Intent();
        setResult(1002, intentMessage);
        finish();
    }


    public void carregarSeriado() {
        final Bundle extras = getIntent().getExtras();
        id = (extras != null) ? extras.getInt("id") : null;

        if (id == null) {
            Seriado seriado = new Seriado();
        } else {
            Seriado seriado = new Seriado();
            SeriadoDAO seriadoDAO = new SeriadoDAO(this);
            seriado = seriadoDAO.getSeriadoById(id);
            etNome.setText(seriado.getNome().toString());

            Calendar cDt= Calendar.getInstance();
            cDt.setTime(seriado.getData());
            dpData.updateDate(cDt.get(Calendar.YEAR), cDt.get(Calendar.MONTH), cDt.get(Calendar.DAY_OF_MONTH));

        }
    }
}
