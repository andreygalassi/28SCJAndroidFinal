package br.fiap.android.trabalhofinal.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import br.fiap.android.trabalhofinal.model.Usuario;

/**
 * Created by usuario on 14/04/2017.
 */

public class UsuarioDAO {
    private DBOpenHelper banco;
    public UsuarioDAO(Context context) {
        banco = new DBOpenHelper(context);
    }
    public static final String TABELA = "usuario";
    public static final String COLUNA_ID = "id";
    public static final String COLUNA_LOGIN = "login";
    public static final String COLUNA_SENHA = "senha";
    public static final String COLUNA_CONECTADO = "conectado";

    public Usuario getUsuarioConectado(){
        SQLiteDatabase db = banco.getReadableDatabase();
        String colunas[] = { COLUNA_ID, COLUNA_LOGIN, COLUNA_SENHA, COLUNA_CONECTADO};
        String query = "SELECT * FROM " + TABELA + " WHERE " + COLUNA_CONECTADO + " = 1 ";
        Cursor cursor = db.rawQuery(query, null);
        Usuario usuario = null;
        if(cursor != null) {
            cursor.moveToFirst();
            Integer _id = cursor.getInt(cursor.getColumnIndex(COLUNA_ID));
            String _login = cursor.getString(cursor.getColumnIndex(COLUNA_LOGIN));
            String _senha = cursor.getString(cursor.getColumnIndex(COLUNA_SENHA));
            Boolean _conectado = intToBool(cursor.getInt(cursor.getColumnIndex(COLUNA_CONECTADO)));
            usuario = new Usuario(_id, _login, _senha, _conectado);
        }
        db.close();
        return usuario;
    }
    public Usuario getById(int id) {
        SQLiteDatabase db = banco.getReadableDatabase();
        String colunas[] = { COLUNA_ID, COLUNA_LOGIN, COLUNA_SENHA, COLUNA_CONECTADO};
        String query = "SELECT * FROM " + TABELA + " WHERE " + COLUNA_ID + " = " + id;
        Cursor cursor = db.rawQuery(query, null);
        Usuario usuario = null;
        if(cursor != null) {
            cursor.moveToFirst();
            Integer _id = cursor.getInt(cursor.getColumnIndex(COLUNA_ID));
            String _login = cursor.getString(cursor.getColumnIndex(COLUNA_LOGIN));
            String _senha = cursor.getString(cursor.getColumnIndex(COLUNA_SENHA));
            Boolean _conectado = intToBool(cursor.getInt(cursor.getColumnIndex(COLUNA_CONECTADO)));
            usuario = new Usuario(_id, _login, _senha, _conectado);
        }
        db.close();
        return usuario;
    }
    public Usuario getByLogin(String login) {
        SQLiteDatabase db = banco.getReadableDatabase();
        String colunas[] = { COLUNA_ID, COLUNA_LOGIN, COLUNA_SENHA, COLUNA_CONECTADO};
        String query = "SELECT * FROM " + TABELA + " WHERE " + COLUNA_LOGIN + " = '" + login + "'";
        Cursor cursor = db.rawQuery(query, null);
        Usuario usuario = null;
        if(cursor != null) {
            cursor.moveToFirst();
            Integer _id = cursor.getInt(cursor.getColumnIndex(COLUNA_ID));
            String _login = cursor.getString(cursor.getColumnIndex(COLUNA_LOGIN));
            String _senha = cursor.getString(cursor.getColumnIndex(COLUNA_SENHA));
            Boolean _conectado = intToBool(cursor.getInt(cursor.getColumnIndex(COLUNA_CONECTADO)));
            usuario = new Usuario(_id, _login, _senha, _conectado);
        }
        db.close();
        return usuario;
    }

    public void save(Usuario usuario) {
        if (usuario.getId()==null){
            add(usuario);
        }else{
            update(usuario);
        }
    }
    public String add(Usuario usuario){
        SQLiteDatabase db = banco.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUNA_LOGIN, usuario.getLogin());
        values.put(COLUNA_SENHA, usuario.getSenha());
        values.put(COLUNA_CONECTADO, boolToInt(usuario.getConectado()));
        long resultado = db.insert(TABELA,  null, values);
        db.close();
        if(resultado == -1) {
            return "Erro ao inserir registro";
        } else {
            return "Registro inserido com sucesso";
        }
    }
    public String update(Usuario usuario){
        SQLiteDatabase db = banco.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUNA_LOGIN, usuario.getLogin());
        values.put(COLUNA_SENHA, usuario.getSenha());
        values.put(COLUNA_CONECTADO, boolToInt(usuario.getConectado()));
        long resultado = db.update(TABELA,  values, "id="+usuario.getId(),null);
        db.close();
        if(resultado == -1) {
            return "Erro ao atualizar registro";
        } else {
            return "Registro atualizar com sucesso";
        }
    }

    private Integer boolToInt(Boolean valor){
        return valor ? 1 : 0;
    }
    private Boolean intToBool(Integer valor){
        return valor == 1 ? true : false;
    }

}
