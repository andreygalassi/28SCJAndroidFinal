package br.fiap.android.trabalhofinal.model;

/**
 * Created by usuario on 15/04/2017.
 */

public class Usuario {

    private Integer id;
    private String login;
    private String senha;
    private Boolean conectado;

    public Usuario(Integer id, String login, String senha, Boolean conectado) {
        this.id = id;
        this.login = login;
        this.senha = senha;
        this.conectado = conectado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Boolean getConectado() {
        return conectado;
    }

    public void setConectado(Boolean conectado) {
        this.conectado = conectado;
    }
}
