package br.fiap.android.trabalhofinal.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import br.fiap.android.trabalhofinal.model.Seriado;

/**
 * Created by usuario on 15/04/2017.
 */

public class SeriadoDAO {
    private SQLiteDatabase db;
    private DBOpenHelper banco;
    public SeriadoDAO(Context context) {
        banco = new DBOpenHelper(context);
    }
    private static final String TABELA = "seriado";
    private static final String COLUNA_ID = "id";
    private static final String COLUNA_NOME = "nome";
    private static final String COLUNA_DATA = "data";
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public String add(Seriado seriado){
        long resultado;
        SQLiteDatabase db = banco.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUNA_NOME, seriado.getNome());
        values.put(COLUNA_DATA, sdf.format(seriado.getData()));
        resultado = db.insert(TABELA,  null, values);
        db.close();
        if(resultado == -1) {
            return "Erro ao inserir registro";
        } else {
            return "Registro inserido com sucesso";
        }
    }
    public String update(Seriado seriado){
        long resultado;
        SQLiteDatabase db = banco.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUNA_NOME, seriado.getNome());
        values.put(COLUNA_DATA, sdf.format(seriado.getData()));
        resultado = db.update(TABELA,  values, "id="+seriado.getId(),null);
        db.close();
        if(resultado == -1) {
            return "Erro ao inserir registro";
        } else {
            return "Registro inserido com sucesso";
        }
    }

    public List<Seriado> getAll() {
        List<Seriado> seriados = new LinkedList<>();
        String rawQuery = "SELECT id, nome, data FROM " + TABELA + " t ";
        SQLiteDatabase db = banco.getReadableDatabase();
        Cursor cursor = db.rawQuery(rawQuery, null);
        Seriado seriado = null;
        if (cursor.moveToFirst()) {
            do {
                seriado = new Seriado();
                seriado.setId(cursor.getInt(0));
                seriado.setNome(cursor.getString(1));
                try {
                    seriado.setData(sdf.parse(cursor.getString(2)));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                seriados.add(seriado);
            } while (cursor.moveToNext());

        }
        return seriados;
    }

    public void deletaSeriado(int id) {
        String where = COLUNA_ID + "=" + id;
        SQLiteDatabase db = banco.getWritableDatabase();
        db.delete(SeriadoDAO.TABELA, where, null);
    }

    public Seriado getSeriadoById(int id) {
        List<Seriado> seriados = new LinkedList<>();
        String rawQuery = "SELECT id, nome, data FROM " +
                SeriadoDAO.TABELA + " t where id = "+id;
        SQLiteDatabase db = banco.getReadableDatabase();
        Cursor cursor = db.rawQuery(rawQuery, null);
        Seriado seriado = null;
        if (cursor.moveToFirst()) {
            do {
                seriado = new Seriado();
                seriado.setId(cursor.getInt(0));
                seriado.setNome(cursor.getString(1));
                try {
                    seriado.setData(sdf.parse(cursor.getString(2)));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                seriados.add(seriado);
            } while (cursor.moveToNext());

        }
        return seriados.get(0);
    }
}

