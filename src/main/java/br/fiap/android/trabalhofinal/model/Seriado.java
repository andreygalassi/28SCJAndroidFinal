package br.fiap.android.trabalhofinal.model;

import java.util.Date;

/**
 * Created by usuario on 15/04/2017.
 */

public class Seriado {
    private int id;
    private String nome;
    private Date data;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

